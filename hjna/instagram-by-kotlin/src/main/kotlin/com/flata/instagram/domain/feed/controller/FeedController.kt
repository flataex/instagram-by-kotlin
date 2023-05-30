package com.flata.instagram.domain.feed.controller

import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.domain.feed.model.dto.FeedRequest
import com.flata.instagram.domain.feed.model.entity.Feed
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/api/feeds")
class FeedController(private val feedService: FeedService) {

    @PostMapping
    fun createFeed(@Valid @RequestBody feedRequest: FeedRequest,
                   @RequestPart("file") file: MultipartFile?,
                   session: HttpSession): ResponseEntity<Feed> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        val feed = if (file != null) {
            feedService.createFeed(jwt, feedRequest, file)
        } else {
            feedService.createFeed(jwt, feedRequest)
        }
        return ResponseEntity.ok(feed)
    }
}