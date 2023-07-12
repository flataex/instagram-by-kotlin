package com.flata.instagram.domain.follow.service

import com.flata.instagram.domain.follow.dto.FollowRequest
import com.flata.instagram.domain.follow.dto.FollowResponse
import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.domain.follow.repository.FollowRepository
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.exception.NoDataException
import com.flata.instagram.global.exception.RedisException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class FollowService(
    private val redisTemplate: RedisTemplate<String, String>,
    private val followRepository: FollowRepository,
    private val userRepository: UserRepository

) {
    @Transactional
    fun follow(followRequest: FollowRequest, userId: Long): FollowResponse =
        run {
            saveFollowOnRedis(
                followRequest,
                userId
            )

            followRepository.save(
                Follow(
                    0L,
                    userRepository.findByIdOrNull(userId) ?: throw NoDataException(),
                    userRepository.findByIdOrNull(followRequest.toUserId) ?: throw NoDataException()
                )
            ).let {
                FollowResponse(true)
            }
        }

    private fun saveFollowOnRedis(followRequest: FollowRequest, userId: Long) =
        runCatching {
            val zSetOperation = redisTemplate.opsForZSet()

            zSetOperation.add(
                "following:${userId}",
                followRequest.toUserId.toString(),
                Timestamp.valueOf(LocalDateTime.now()).time.toDouble()
            )

            zSetOperation.add(
                "followers:${followRequest.toUserId}",
                userId.toString(),
                Timestamp.valueOf(LocalDateTime.now()).time.toDouble()
            )
        }.onFailure {
            throw RedisException()
        }

    @Transactional
    fun unfollow(followRequest: FollowRequest, userId: Long) =
        run {
            deleteFollowOnRedis(
                followRequest,
                userId
            )

            followRepository.findByIdOrNull(followRequest.id)
                ?.delete()
                ?: throw NoDataException()
        }

    private fun deleteFollowOnRedis(followRequest: FollowRequest, userId: Long) =
        runCatching {
            val zSetOperations = redisTemplate.opsForZSet()

            zSetOperations.remove(
                "following:${userId}",
                followRequest.toUserId.toString(),
            )

            zSetOperations.remove(
                "followers:${followRequest.toUserId}",
                userId.toString(),
            )
        }.onFailure {
            throw RedisException()
        }
}