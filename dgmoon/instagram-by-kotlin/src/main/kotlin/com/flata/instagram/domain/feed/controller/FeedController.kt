package com.flata.instagram.domain.feed.controller

import com.flata.instagram.domain.feed.dto.FeedRequest
import com.flata.instagram.domain.feed.dto.FeedResponse
import com.flata.instagram.domain.feed.service.FeedService
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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
    fun getFeeds(
        @PageableDefault(
            size = 10,
            page = 1,
            sort = ["id"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): ResponseEntity<List<FeedResponse>> =
        ResponseEntity.ok(
            feedService.getFeeds(pageable)
        )

    @GetMapping("/{id}")
    fun getFeed(@PathVariable id: Long): ResponseEntity<FeedResponse> =
        ResponseEntity.ok(
            feedService.getFeed(id)
        )

    @PostMapping
    fun saveFeed(@Valid @RequestBody feedRequest: FeedRequest): ResponseEntity<Unit> =
        feedService.saveFeed(feedRequest).let {
            ResponseEntity.created(
                URI.create(
                    "/feeds/$it"
                )
            ).build()
        }

    @DeleteMapping
    fun deleteFeed(@Valid @RequestBody feedRequest: FeedRequest): ResponseEntity<Unit> =
        run {
            feedService.deleteFeed(feedRequest)
            ResponseEntity.noContent().build()
        }
}
