package com.flata.instagram.domain.feed.controller

import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.domain.feed.model.dto.FeedRequest
import com.flata.instagram.domain.feed.model.dto.FeedResponse
import com.flata.instagram.domain.feed.model.entity.Feed
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.lang.Nullable
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/api/feeds")
class FeedController(private val feedService: FeedService) {


    @PostMapping
    fun createFeed( @Valid feedRequest: FeedRequest,
                   session: HttpSession): ResponseEntity<Feed> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        feedService.createFeed(jwt, feedRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/{feedId}")
    fun findById(
        @PathVariable(value = "feedId") feedId: Long
    ): ResponseEntity<FeedResponse> {
        return ResponseEntity(HttpStatus.OK)
    }
}