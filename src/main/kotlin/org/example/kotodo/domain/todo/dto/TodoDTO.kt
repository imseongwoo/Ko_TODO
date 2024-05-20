package org.example.kotodo.domain.todo.dto

data class TodoDTO(
    val title: String,
    val content: String,
    val writer: String,
    val complete: Boolean
)
