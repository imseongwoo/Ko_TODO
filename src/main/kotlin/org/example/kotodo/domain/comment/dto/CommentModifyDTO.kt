package org.example.kotodo.domain.comment.dto

data class CommentModifyDTO(
    val content: String,
    val password: String,
    val writer: String
)
