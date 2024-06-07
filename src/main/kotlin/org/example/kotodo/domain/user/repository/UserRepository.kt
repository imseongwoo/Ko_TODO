package org.example.kotodo.domain.user.repository

import org.example.kotodo.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean

    fun findByEmail(email: String): User?
}