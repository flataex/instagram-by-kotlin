package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.controller.dto.FeedSaveRequest
import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.user.converter.toDTO
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.exception.feed.FeedNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FeedService(
    private val feedRepository: FeedRepository
) {

    fun save(request: FeedSaveRequest, user: User): Feed =
        feedRepository.save(
            Feed(
                user = user,
                content = request.content
            )
        )

    @Transactional(readOnly = true)
    fun findAll(user: User): List<Feed> =
        feedRepository.findByUser(user)

    @Transactional(readOnly = true)
    fun findById(feedId: Long): Feed =
        feedRepository.findByIdOrNull(feedId) ?: throw FeedNotFoundException()

    fun deleteBy(feed: Feed) =
        feedRepository.delete(feed)
}