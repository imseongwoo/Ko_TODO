package org.example.kotodo.domain.todo.dto

import java.util.*

data class TodoDTO(
    val title: String,
    val content: String,
    val createdDate: Date,
    val writer: String
)
