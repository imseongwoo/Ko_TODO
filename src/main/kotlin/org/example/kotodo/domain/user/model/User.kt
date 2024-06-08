package org.example.kotodo.domain.user.model

import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val role: UserRole,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

