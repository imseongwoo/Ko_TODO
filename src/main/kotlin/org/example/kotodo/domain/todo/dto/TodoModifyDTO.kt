package org.example.kotodo.domain.todo.dto

data class TodoModifyDTO(
    val title: String,
    val content: String,
    val writer: String,
)
