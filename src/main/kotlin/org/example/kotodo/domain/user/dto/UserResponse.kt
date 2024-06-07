package org.example.kotodo.domain.user.dto

import org.example.kotodo.domain.user.model.User

data class UserResponse(
    val id: Long,
    val email: String,
    val role: String,
) {
    companion object {
        fun fromEntity(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                email = user.email,
                role = user.role.name
            )
        }
    }
}
