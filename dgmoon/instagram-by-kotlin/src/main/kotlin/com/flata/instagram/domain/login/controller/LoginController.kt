package com.flata.instagram.domain.login.controller

import com.flata.instagram.domain.login.dto.LoginRequest
import com.flata.instagram.domain.login.service.LoginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
class LoginController(
    private val loginService: LoginService,
) {
    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest, session: HttpSession): ResponseEntity<Long?> {
        return ResponseEntity.ok(loginService.login(loginRequest, session))
    }

    @PostMapping("/logout")
    fun logout(session: HttpSession): ResponseEntity<Any> {
        return ResponseEntity.ok(loginService.logout(session))
    }
}