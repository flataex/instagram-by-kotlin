package com.flata.instagram.domain.user.controller

import com.flata.instagram.domain.user.dto.UserRequest
import com.flata.instagram.domain.user.dto.UserResponse
import com.flata.instagram.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> =
        userService.getUser(id)

    @PostMapping
    fun saveUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> =
        userService.saveUser(userRequest)

    @PutMapping
    fun updateUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> =
        userService.updateUser(userRequest)

    @DeleteMapping
    fun deleteUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> =
        userService.deleteUser(userRequest)
}
