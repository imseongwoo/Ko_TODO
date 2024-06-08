package org.example.kotodo.domain.socialmember.service

import org.example.kotodo.client.dto.KakaoLoginUserInfoResponse
import org.example.kotodo.domain.socialmember.entity.SocialMember
import org.example.kotodo.domain.socialmember.repository.SocialMemberRepository
import org.springframework.stereotype.Service

@Service
class SocialMemberDomainService(
    private val socialMemberRepository: SocialMemberRepository
) {

    fun registerIfAbsent(userInfo: KakaoLoginUserInfoResponse): SocialMember {
        return socialMemberRepository.findByProviderNameAndProviderId("KAKAO", userInfo.id.toString())
            ?: socialMemberRepository.save(
                SocialMember(
                    providerName = "KAKAO",
                    providerId = userInfo.id.toString(),
                    nickname = userInfo.properties.nickname
                )
            )
    }
}