package com.flata.instagram.domain.like.service

import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.domain.like.model.dto.LikeRequest
import com.flata.instagram.domain.like.model.dto.LikeResponse
import com.flata.instagram.domain.like.model.entity.Like
import com.flata.instagram.domain.like.repository.LikeRepository
import com.flata.instagram.domain.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeService (
    private val likeRepository: LikeRepository,
    private val userRepository: UserRepository,
    private val feedRepository: FeedRepository
) {

    @Transactional
    fun like(likeRequest: LikeRequest,
             userId: Long) {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("유저가 존재하지 않습니다.") }
        val feed = feedRepository.findById(likeRequest.feedId).orElseThrow { IllegalArgumentException("피드가 존재하지 않습니다.") }

        if (likeRepository.findByUserAndFeed(user, feed) != null) {
            throw IllegalArgumentException("Already liked")
        }

        likeRepository.save(Like(user = user, feed = feed))
    }

    @Transactional
    fun disableLike(likeRequest: LikeRequest,
                    userId: Long) {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("유저가 존재하지 않습니다.") }
        val feed = feedRepository.findById(likeRequest.feedId).orElseThrow { IllegalArgumentException("피드가 존재하지 않습니다.") }
        val like = likeRepository.findByUserAndFeed(user, feed) ?: throw IllegalArgumentException("좋아요가 존재하지 않습니다.")

        likeRepository.delete(like)
    }

    @Transactional
    fun getLikeUsers(feedId: Long) : List<LikeResponse> {
        val feed = feedRepository.findById(feedId).orElseThrow { IllegalArgumentException("피드가 존재하지 않습니다.") }
        val likes = likeRepository.findByFeed(feed)

        return likes.map { LikeResponse(it.user.email, it.user.nickname) }
    }
}