package org.example.kotodo.domain.todo.dto

data class TodoCreateDTO(
    val title: String,
    val content: String,
    val writer: String
)
