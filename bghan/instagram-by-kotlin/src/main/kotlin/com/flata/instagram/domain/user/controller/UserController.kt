package com.flata.instagram.domain.user.controller

import com.flata.instagram.domain.user.controller.dto.SignInRequest
import com.flata.instagram.domain.user.controller.dto.SignInResponse
import com.flata.instagram.domain.user.controller.dto.SignUpRequest
import com.flata.instagram.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/sign-up")
    fun signUp(
        @Valid @RequestBody request: SignUpRequest
    ): ResponseEntity<Void> {
        userService.signUp(request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody request: SignInRequest
    ): ResponseEntity<SignInResponse> {
        return ResponseEntity.ok(userService.signIn(request))
    }
}
