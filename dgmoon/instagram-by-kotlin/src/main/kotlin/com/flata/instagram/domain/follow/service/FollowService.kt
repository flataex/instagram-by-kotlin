package com.flata.instagram.domain.follow.service

import com.flata.instagram.domain.follow.dto.FollowRequest
import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.domain.follow.repository.FollowRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class FollowService(
    private val redisTemplate: RedisTemplate<String, String>,
    private val followRepository: FollowRepository

) {
    fun follow(followRequest: FollowRequest, userId: Long): Boolean {
        val zSetOperations = redisTemplate.opsForZSet()
        zSetOperations.operations.multi()

        zSetOperations.add(
            "following:".plus(userId),
            followRequest.toUserId.toString(),
            Timestamp.valueOf(LocalDateTime.now()).time.toDouble()
        )

        zSetOperations.add(
            "followers:".plus(followRequest.toUserId),
            userId.toString(),
            Timestamp.valueOf(LocalDateTime.now()).time.toDouble()
        )

        followRepository.save(
            Follow(
                0,
                fromUserId = userId,
                followRequest.toUserId
            )
        )
        return true
    }

    fun unfollow(followRequest: FollowRequest, userId: Long): Boolean {
        val zSetOperations = redisTemplate.opsForZSet()
        zSetOperations.operations.multi()

        zSetOperations.remove(
            "following:".plus(userId),
            followRequest.toUserId.toString(),
        )

        zSetOperations.remove(
            "followers:".plus(followRequest.toUserId),
            userId.toString(),
        )

        val follow = followRepository.findByIdOrNull(followRequest.id) ?: throw NoDataException()

        follow.delete()

        return true
    }
}