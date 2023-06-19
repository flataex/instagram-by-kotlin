package com.flata.instagram.domain.follow.controller

import com.flata.instagram.domain.follow.service.FollowService
import com.flata.instagram.global.model.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/follows")
class FollowController(
    private val followService: FollowService
) {

    @PostMapping("/{id}")
    fun save(
        @LoginUser userId: Long,
        @PathVariable(value = "id") toUserId: Long
    ): ResponseEntity<Long> {
        return ResponseEntity(followService.save(userId, toUserId), HttpStatus.CREATED)
    }

    @PostMapping("/{id}")
    fun delete(
        @LoginUser userId: Long,
        @PathVariable(value = "id") toUserId: Long
    ): ResponseEntity<Void> {
        followService.delete(userId, toUserId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}