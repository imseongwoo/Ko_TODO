package org.example.kotodo.api.controller

import jakarta.servlet.http.HttpServletResponse
import org.example.kotodo.api.service.KakaoOAuth2LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class KakaoOAuth2LoginController(
    private val kakaoOAuth2LoginService: KakaoOAuth2LoginService
) {

    @GetMapping("/oauth2/login/kakao")
    fun redirectLoginPage(response: HttpServletResponse) {
        val loginPageUrl = kakaoOAuth2LoginService.generateLoginPageUrl()
        response.sendRedirect(loginPageUrl)
    }

    @GetMapping("/oauth2/login/callback")
    fun callback(
        @RequestParam code: String
    ): ResponseEntity<String> {
        val accessToken = kakaoOAuth2LoginService.login(code)
        return ResponseEntity.ok(accessToken)
    }
}