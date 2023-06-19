package com.flata.instagram.domain.like.controller

import com.flata.instagram.domain.like.service.LikeService
import com.flata.instagram.global.model.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/likes")
class LikeController(
    private val likeService: LikeService
) {

    @PostMapping("/{feed-id}")
    fun save(
        @LoginUser userId: Long,
        @PathVariable(value = "feed-id") feedId: Long
    ): ResponseEntity<Long> {
        return ResponseEntity(likeService.save(userId, feedId), HttpStatus.CREATED)
    }

    @DeleteMapping("/{feed-id}")
    fun delete(
        @LoginUser userId: Long,
        @PathVariable(value = "feed-id") feedId: Long
    ): ResponseEntity<Long> {
        likeService.delete(userId, feedId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}