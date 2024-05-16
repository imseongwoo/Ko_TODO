package org.example.kotodo.domain.todo.repository

import org.example.kotodo.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository: JpaRepository<Todo, Long> {
}