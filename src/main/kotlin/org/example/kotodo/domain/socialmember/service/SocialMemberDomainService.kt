package org.example.kotodo.domain.socialmember.service

import org.example.kotodo.client.dto.KakaoLoginUserInfoResponse
import org.example.kotodo.domain.socialmember.entity.SocialMember
import org.example.kotodo.domain.socialmember.repository.SocialMemberRepository
import org.example.kotodo.domain.user.dto.SignUpRequest
import org.example.kotodo.domain.user.service.UserService
import org.springframework.stereotype.Service

@Service
class SocialMemberDomainService(
    private val socialMemberRepository: SocialMemberRepository,
    private val userService: UserService,
) {

    fun registerIfAbsent(userInfo: KakaoLoginUserInfoResponse): SocialMember {
        userService.signUp(SignUpRequest(email = userInfo.properties.nickname, password = userInfo.id.toString(), role = "USER"))
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