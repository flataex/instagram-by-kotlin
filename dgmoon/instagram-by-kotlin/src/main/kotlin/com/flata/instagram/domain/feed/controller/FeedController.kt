package com.flata.instagram.domain.feed.controller

import com.flata.instagram.domain.feed.dto.FeedRequest
import com.flata.instagram.domain.feed.dto.FeedResponse
import com.flata.instagram.domain.feed.service.FeedService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/feeds")
class FeedController(
    private val feedService: FeedService
) {
    @GetMapping
    fun getFeeds(): ResponseEntity<List<FeedResponse>> =
        ResponseEntity.ok(feedService.getFeeds())

    @GetMapping("/{id}")
    fun getFeed(@PathVariable id: Long): ResponseEntity<FeedResponse> =
        ResponseEntity.ok(feedService.getFeed(id))

    @PostMapping
    fun saveFeed(@Valid @RequestBody feedRequest: FeedRequest): ResponseEntity<Any> {
        val uri = feedService.saveFeed(feedRequest).let {
            URI.create("/feed".plus(it))
        }.also {
            ResponseEntity.created(it)
        }

        return ResponseEntity.created(uri).build()
    }

    @DeleteMapping
    fun deleteFeed(@Valid @RequestBody feedRequest: FeedRequest): ResponseEntity<Any> {
        feedService.deleteFeed(feedRequest)
        return ResponseEntity.noContent().build()
    }
}
