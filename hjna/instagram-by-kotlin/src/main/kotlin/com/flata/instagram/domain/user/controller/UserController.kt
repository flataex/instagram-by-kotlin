package com.flata.instagram.domain.user.controller

import com.flata.instagram.domain.user.model.dto.UserLoginRequest
import com.flata.instagram.domain.user.model.dto.UserRegisterRequest
import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.domain.user.service.UserService
import com.flata.instagram.global.utils.JwtProvider
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService,
                     private val jwtProvider: JwtProvider
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userRequest: UserRegisterRequest): ResponseEntity<User> {
        val user = userService.registerUser(userRequest)
        return ResponseEntity(user, HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: UserLoginRequest, session: HttpSession): ResponseEntity<Any> {
        return try {
            val user = userService.login(loginRequest)
            val jwt = user.id?.let { jwtProvider.generateJwtToken(it) }
            session.setAttribute("USER_TOKEN", jwt)
            ResponseEntity.ok().body(user.id)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.message)
        }
    }
}