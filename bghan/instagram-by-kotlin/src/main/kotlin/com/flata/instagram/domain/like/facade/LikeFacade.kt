package com.flata.instagram.domain.like.facade

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.domain.like.controller.dto.LikeRequest
import com.flata.instagram.domain.like.service.LikeService
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.domain.user.service.UserService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Component
@Transactional
@Validated
class LikeFacade(
    private val userService: UserService,
    private val feedService: FeedService,
    private val likeService: LikeService
) {
    fun save(
       @Valid request: LikeRequest
    ) {
        val user: User = userService.findBy(request.userId)
        val feed: Feed = feedService.findById(request.feedId)

        likeService.save(user, feed)
    }

    fun dislike(
        userId: Long,
        feedId: Long
    ) {
        likeService.delete(userId, feedId)
    }
}