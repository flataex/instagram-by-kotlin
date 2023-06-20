package com.flata.instagram.domain.user.controller

import com.flata.instagram.domain.user.dto.UserRequest
import com.flata.instagram.domain.user.dto.UserResponse
import com.flata.instagram.domain.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getUsers(): ResponseEntity<List<UserResponse>> =
        ResponseEntity.ok(userService.getUsers())

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> =
        ResponseEntity.ok(userService.getUser(id))

    @PostMapping
    fun saveUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> =
        ResponseEntity.created(
            userService.saveUser(userRequest).let {
                URI.create("/users/".plus(it))
            }
        ).build()

    @PutMapping
    fun updateUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> {
        userService.updateUser(userRequest)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    fun deleteUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> {
        userService.deleteUser(userRequest)
        return ResponseEntity.noContent().build()
    }
}
