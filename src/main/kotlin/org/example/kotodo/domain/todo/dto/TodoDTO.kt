package org.example.kotodo.domain.todo.dto

import org.example.kotodo.domain.comment.dto.CommentDTO

data class TodoDTO(
    val title: String,
    val content: String,
    val writer: String,
    val complete: Boolean,
    val comments: List<CommentDTO>
)
