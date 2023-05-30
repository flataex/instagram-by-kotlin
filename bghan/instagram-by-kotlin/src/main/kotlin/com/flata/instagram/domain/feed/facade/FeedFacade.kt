package com.flata.instagram.domain.feed.facade

import com.flata.instagram.domain.converter.toDTOWith
import com.flata.instagram.domain.feed.controller.dto.FeedDeletionRequest
import com.flata.instagram.domain.feed.controller.dto.FeedResponse
import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.domain.file.service.FileService
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.domain.user.service.UserService
import com.flata.instagram.global.exception.feed.NotFeedOwnerException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Component
@Transactional
@Validated
class FeedFacade(
    private val feedService: FeedService,
    private val userService: UserService,
    private val fileService: FileService
) {

    fun save(userId: Long, request: FeedSaveRequest) {
        val user: User = userService.getUserBy(userId)
        val feed: Feed = feedService.save(request, user)
        fileService.saveAll(request.images, feed)
    }

    @Transactional(readOnly = true)
    fun findByUser(userId: Long): List<FeedResponse> {
        val user: User = userService.getUserBy(userId)

        return feedService.findAll(user)
            .stream()
            .map { findByFeed(it.id) }
            .toList()
    }

    @Transactional(readOnly = true)
    fun findByFeed(feedId: Long): FeedResponse {
        val feed: Feed = feedService.findById(feedId)
        val images: List<String> = fileService.findBy(feed)

        return feed.toDTOWith(images)
    }

    fun deleteById(@Valid request: FeedDeletionRequest) {
        val feed: Feed = feedService.findById(request.feedId)

        fileService.deleteBy(feed)
        feedService.deleteBy(feed)
    }
}