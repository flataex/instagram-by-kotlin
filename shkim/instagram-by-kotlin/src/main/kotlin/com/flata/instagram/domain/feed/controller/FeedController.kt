package com.flata.instagram.domain.feed.controller

import com.flata.instagram.domain.feed.controller.dto.FeedResponse
import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.global.model.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/feeds")
class FeedController(
    private val feedService: FeedService
) {

    @GetMapping
    fun findAll(
        @LoginUser userId: Long,
    ): ResponseEntity<List<FeedResponse>> {
        return ResponseEntity(feedService.findAll(userId), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable(value = "id") feedId: Long
    ): ResponseEntity<FeedResponse> {
        return ResponseEntity(feedService.findById(feedId), HttpStatus.OK)
    }

    @PostMapping
    fun save(
        @LoginUser userId: Long,
        @ModelAttribute request: FeedSaveRequest
    ): ResponseEntity<Long> {
        return ResponseEntity(feedService.save(userId, request), HttpStatus.CREATED)
    }
}