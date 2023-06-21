package com.flata.instagram.domain.user.controller

import com.flata.instagram.domain.user.controller.dto.UserLoginRequest
import com.flata.instagram.domain.user.controller.dto.UserSaveRequest
import com.flata.instagram.domain.user.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun save(
        @RequestBody request: UserSaveRequest
    ): ResponseEntity<Long> {
        return ResponseEntity(userService.save(request), HttpStatus.CREATED)
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: UserLoginRequest
    ): ResponseEntity<Long> {
        return ResponseEntity(userService.login(request), HttpStatus.OK)
    }
}