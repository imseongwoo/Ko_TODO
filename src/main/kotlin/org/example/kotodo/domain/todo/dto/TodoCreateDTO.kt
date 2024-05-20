package org.example.kotodo.domain.todo.dto

import java.util.*

data class TodoCreateDTO(
    val title: String,
    val content: String,
    val createdDate: Date,
    val writer: String
)
