package com.flata.instagram.domain.follow.service

import com.flata.instagram.domain.follow.dto.FollowRequest
import com.flata.instagram.domain.follow.dto.FollowResponse
import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.domain.follow.repository.FollowRepository
import com.flata.instagram.domain.user.repository.UserRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
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
    fun follow(followRequest: FollowRequest, userId: Long): ResponseEntity<FollowResponse> =
        runCatching {
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
                        0L,
                        userRepository.findByIdOrNull(userId) ?: throw NoDataException(),
                        userRepository.findByIdOrNull(followRequest.toUserId) ?: throw NoDataException()
                    )
                ).let {
                    ResponseEntity.ok(
                        FollowResponse(
                            true
                        )
                    )
                }
        }.onFailure {
            throw Exception()
        }.getOrThrow()

    @Transactional
    fun unfollow(followRequest: FollowRequest, userId: Long): ResponseEntity<Unit> =
        runCatching {
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

            followRepository.findByIdOrNull(followRequest.id)
                ?.let {
                    it.delete()
                    ResponseEntity.noContent().build<Unit>()
                }
                ?: throw NoDataException()
        }.onFailure {
            throw Exception()
        }.getOrThrow()

}