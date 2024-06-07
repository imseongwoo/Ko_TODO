package org.example.kotodo.domain.user.dto

data class UserResponse(
    val id: Long,
    val email: String,
    val role: String,
)
