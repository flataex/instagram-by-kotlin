package com.flata.instagram.domain.like.service

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.like.model.Like
import com.flata.instagram.domain.like.repository.LikeRepository
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.exception.like.LikeNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class LikeService(
    private val likeRepository: LikeRepository
) {
    fun save(
        user: User,
        feed: Feed
    ) {
        likeRepository.save(
            Like(
                user = user,
                feed = feed
            )
        )
    }

    @Transactional(readOnly = true)
    fun existsBy(
        userId: Long,
        feedId: Long
    ): Boolean = likeRepository.existsByUserIdAndFeedId(userId, feedId)

    fun delete(
        userId: Long,
        feedId: Long
    ) {
        likeRepository.findByUserIdAndFeedId(userId, feedId)
            ?.delete()
            ?: throw LikeNotFoundException()
    }
}