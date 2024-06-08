package org.example.kotodo.domain.todo.repository

import org.example.kotodo.domain.todo.dto.SearchKeywordDTO
import org.example.kotodo.domain.todo.model.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface QueryDslTodoRepository {
    fun getTodoList(pageable: Pageable, searchKeywordDTO: SearchKeywordDTO?): Page<Todo>
}