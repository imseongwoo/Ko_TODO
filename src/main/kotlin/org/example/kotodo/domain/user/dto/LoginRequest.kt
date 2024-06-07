package org.example.kotodo.domain.user.dto

data class LoginRequest(
    val email: String,
    val password: String,
    val role: String
)