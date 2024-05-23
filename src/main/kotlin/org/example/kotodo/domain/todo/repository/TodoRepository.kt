package org.example.kotodo.domain.todo.repository

import org.example.kotodo.domain.todo.model.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    @EntityGraph(attributePaths = ["comments"])
    fun findByWriter(writer: String, pageable: Pageable): Page<Todo>

    @EntityGraph(attributePaths = ["comments"])
    override fun findAll(pageable: Pageable): Page<Todo>
}