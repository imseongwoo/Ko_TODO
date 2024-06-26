package org.example.kotodo.domain.socialmember.repository

import org.example.kotodo.domain.socialmember.entity.SocialMember
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SocialMemberRepository : JpaRepository<SocialMember, Long> {
    fun findByProviderNameAndProviderId(providerName: String, providerId: String): SocialMember?
}