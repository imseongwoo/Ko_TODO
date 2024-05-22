package org.example.kotodo.domain.todo.dto

import jakarta.validation.constraints.Size

data class TodoCreateDTO(
    @field:Size(min = 1, max = 200)
    val title: String,

    @field:Size(min = 1, max = 1000)
    val content: String,

    val writer: String
)
