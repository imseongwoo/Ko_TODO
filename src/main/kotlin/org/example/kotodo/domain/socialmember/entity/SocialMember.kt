package org.example.kotodo.domain.socialmember.entity

import jakarta.persistence.*

@Entity
class SocialMember(
    @Column(name = "provider_name", nullable = false)
    val providerName: String,
    @Column(name = "provider_id", nullable = false)
    val providerId: String,
    @Column(name = "nickname", nullable = true)
    var nickname: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}