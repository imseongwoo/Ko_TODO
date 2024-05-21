package org.example.kotodo.domain.comment.dto

data class CommentDTO(
    val id: Long?,
    val content: String,
    val writer: String
)
