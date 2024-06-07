package org.example.kotodo.domain.user.service

import org.example.kotodo.domain.common.exception.InvalidCredentialException
import org.example.kotodo.domain.common.exception.ModelNotFoundException
import org.example.kotodo.domain.user.dto.LoginRequest
import org.example.kotodo.domain.user.dto.LoginResponse
import org.example.kotodo.domain.user.dto.SignUpRequest
import org.example.kotodo.domain.user.dto.UserResponse
import org.example.kotodo.domain.user.model.User
import org.example.kotodo.domain.user.model.UserRole
import org.example.kotodo.domain.user.repository.UserRepository
import org.example.kotodo.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
) : UserService {

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        if (user.role.name != request.role || !passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialException()
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                role = user.role.name,
            )
        )
    }

    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        val user = userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                role = when (request.role) {
                    "USER" -> UserRole.USER
                    "ADMIN" -> UserRole.ADMIN
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        )

        return UserResponse.fromEntity(user)
    }

}