package com.flata.instagram.domain.feed.controller

import com.flata.instagram.domain.feed.controller.dto.FeedDeletionRequest
import com.flata.instagram.domain.feed.controller.dto.FeedResponse
import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.facade.FeedFacade
import com.flata.instagram.global.model.Authorization
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/feeds")
class FeedController(
    private val feedFacade: FeedFacade
) {

    @PostMapping
    fun save(
        @Authorization userId: Long,
        @Valid request: FeedSaveRequest
    ): ResponseEntity<Void> {
        feedFacade.save(userId, request)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping
    fun findByUser(@Authorization userId: Long): ResponseEntity<List<FeedResponse>> =
        ResponseEntity.ok(feedFacade.findByUser(userId))

    @GetMapping("/{feedId}")
    fun findById(
        @PathVariable(value = "feedId") feedId: Long
    ): ResponseEntity<FeedResponse> =
        ResponseEntity.ok(feedFacade.findByFeed(feedId))

    @DeleteMapping("/{feedId}")
    fun deleteById(
        @Authorization userId: Long,
        @PathVariable(value = "feedId") feedId: Long
    ): ResponseEntity<Void> {
        feedFacade.deleteById(
            FeedDeletionRequest(
                userId = userId,
                feedId = feedId
            )
        )
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}