package com.flata.instagram.domain.user.controller

import com.flata.instagram.domain.user.model.dto.UserRegisterRequest
import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userRequest: UserRegisterRequest): ResponseEntity<User> {
        val user = userService.registerUser(userRequest)
        return ResponseEntity(user, HttpStatus.CREATED)
    }
}