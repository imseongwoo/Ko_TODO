package org.example.kotodo.domain.todo.repository

import org.example.kotodo.domain.todo.model.Todo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByWriter(writer: String, pageable: Pageable): Page<Todo>
    override fun findAll(pageable: Pageable): Page<Todo>
}