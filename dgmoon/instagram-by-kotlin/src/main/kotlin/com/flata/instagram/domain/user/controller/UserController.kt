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
    fun getUsers() =
        ResponseEntity.ok(userService.getUsers())

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponse> =
        ResponseEntity.ok(
            userService.getUser(id)
        )

    @PostMapping
    fun saveUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> =
        userService.saveUser(userRequest)
            .let {
                ResponseEntity.created(
                    URI.create("/user/$it")
                ).build()
            }

    @PutMapping
    fun updateUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> =
        run {
            userService.updateUser(userRequest)
            ResponseEntity.noContent().build()
        }

    @DeleteMapping
    fun deleteUser(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<Unit> =
        run {
            userService.deleteUser(userRequest)
            ResponseEntity.noContent().build()
        }
}
