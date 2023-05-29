package com.flata.instagram.domain.feed.controller

import com.flata.instagram.domain.feed.controller.dto.FeedResponse
import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.service.FeedService
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
        @RequestHeader(value = "Authorization") userId: String,
    ): ResponseEntity<List<FeedResponse>> {
        val userId2 = userId.split(" ")[1].toLong()
        return ResponseEntity(feedService.findAll(userId2), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findById(
        @PathVariable(value = "id") feedId: Long
    ): ResponseEntity<FeedResponse> {
        return ResponseEntity(feedService.findById(feedId), HttpStatus.OK)
    }

    @PostMapping
    fun save(
        @RequestHeader(value = "Authorization") userId: String,
        @ModelAttribute request: FeedSaveRequest
    ): ResponseEntity<Long> {
        val userId2 = userId.split(" ")[1].toLong()
        return ResponseEntity(feedService.save(userId2, request), HttpStatus.CREATED)
    }
}