package com.flata.instagram.domain.login.controller

import com.flata.instagram.domain.login.dto.LoginRequest
import com.flata.instagram.domain.login.service.LoginService
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
class LoginController(
    private val loginService: LoginService,
) {
    @PostMapping("/login")
    fun login(loginRequest: LoginRequest, session: HttpSession) {
        loginService.login(loginRequest, session)
    }

    @PostMapping("/logout")
    fun logout(session: HttpSession) {
        loginService.logout(session)
    }
}