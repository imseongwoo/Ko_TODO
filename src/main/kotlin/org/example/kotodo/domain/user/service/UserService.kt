package org.example.kotodo.domain.user.service

import org.example.kotodo.domain.user.dto.LoginRequest
import org.example.kotodo.domain.user.dto.LoginResponse
import org.example.kotodo.domain.user.dto.SignUpRequest
import org.example.kotodo.domain.user.dto.UserResponse

interface UserService {

    fun signUp(request: SignUpRequest): UserResponse

    fun login(request: LoginRequest): LoginResponse
}