package com.flata.instagram.domain.follow.controller

import com.flata.instagram.domain.follow.controller.dto.FollowSaveRequest
import com.flata.instagram.domain.follow.service.FollowService
import com.flata.instagram.global.model.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/follows")
class FollowController(
    private val followService: FollowService
) {

    @PostMapping
    fun save(
        @LoginUser userId: Long,
        @RequestBody request: FollowSaveRequest
    ): ResponseEntity<Long> {
        return ResponseEntity(followService.save(userId, request), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @LoginUser userId: Long,
        @PathVariable(value = "id") followId: Long
    ): ResponseEntity<Void> {
        followService.delete(userId, followId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}