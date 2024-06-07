package org.example.kotodo.domain.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val role: String,
)
