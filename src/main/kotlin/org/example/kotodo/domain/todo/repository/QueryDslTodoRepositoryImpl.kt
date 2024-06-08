package org.example.kotodo.domain.todo.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.StringExpression
import org.example.kotodo.domain.todo.dto.SearchKeywordDTO
import org.example.kotodo.domain.todo.model.QTodo
import org.example.kotodo.domain.todo.model.Todo
import org.example.kotodo.infra.querydsl.QueryDslSupport
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import kotlin.reflect.full.memberProperties

class QueryDslTodoRepositoryImpl : QueryDslTodoRepository, QueryDslSupport() {

    private val todo = QTodo.todo
    private val keywordMap = mapOf(
        "title" to todo.title,
        "content" to todo.content
    )

    override fun getTodoList(pageable: Pageable, searchKeywordDTO: SearchKeywordDTO?): Page<Todo> {
        val whereClause = BooleanBuilder(todo.id.isNotNull)
        searchKeywordDTO?.let { keywords ->
            val keywordBuilder = BooleanBuilder()
            SearchKeywordDTO::class.memberProperties.forEach { property ->
                val keyword = property.get(keywords)?.toString()
                if (keyword != null) {
                    val expression = keywordMap[property.name]
                    if (expression is StringExpression) {
                        keywordBuilder.or(expression.containsIgnoreCase(keyword))
                    }
                }
            }
            whereClause.and(keywordBuilder)
        }

        val content = queryFactory.selectFrom(todo)
            .where(whereClause)
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
        val total = queryFactory.select(todo.count()).from(todo).where(whereClause).fetchOne() ?: 0L

        return PageImpl(content, pageable, total)
    }
}