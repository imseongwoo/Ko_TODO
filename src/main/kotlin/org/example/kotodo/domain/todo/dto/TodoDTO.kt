package org.example.kotodo.domain.todo.dto

import java.util.*

data class TodoDTO(
    var title: String,
    var content: String,
    val createdDate: Date,
    var writer: String
)
