package org.example.kotodo.api.service

import org.example.kotodo.client.KakaoOAuth2LoginClient
import org.example.kotodo.domain.socialmember.service.SocialMemberDomainService
import org.example.kotodo.infra.security.jwt.JwtPlugin
import org.springframework.stereotype.Service

@Service
class KakaoOAuth2LoginService(
    private val kakaoOAuth2LoginClient: KakaoOAuth2LoginClient,
    private val socialMemberDomainService: SocialMemberDomainService,
    private val jwtPlugin: JwtPlugin
) {
    fun generateLoginPageUrl(): String {
        return kakaoOAuth2LoginClient.generateLoginPageUrl()
    }

    fun login(code: String): String {
        return kakaoOAuth2LoginClient.getAccessToken(code)
            .let { kakaoOAuth2LoginClient.retrieveUserInfo(it) }
            .let { socialMemberDomainService.registerIfAbsent(it) }
            .let { jwtPlugin.generateAccessToken(it.id!!.toString(), it.nickname) }
    }
}