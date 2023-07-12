package com.flata.instagram.domain.like.service

import com.flata.instagram.domain.like.dto.LikeRequest
import com.flata.instagram.domain.like.dto.LikeResponse
import com.flata.instagram.domain.like.model.Like
import com.flata.instagram.domain.like.repository.LikeRepository
import com.flata.instagram.global.exception.NoDataException
import com.flata.instagram.global.exception.RedisException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class LikeService(
    private val likeRepository: LikeRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {
    @Transactional
    fun like(likeRequest: LikeRequest, userId: Long): LikeResponse =
        run {
            saveLikeOnRedis(likeRequest, userId)

            likeRepository.save(
                Like(
                    0,
                    userId,
                    likeRequest.feedId
                )
            )
            LikeResponse(true)
        }

    private fun saveLikeOnRedis(likeRequest: LikeRequest, userId: Long) =
        runCatching {
            redisTemplate.opsForZSet()
                .add(
                    "likes:${likeRequest.feedId}",
                    userId.toString(),
                    Timestamp.valueOf(LocalDateTime.now()).time.toDouble()
                )
        }.onFailure {
            throw RedisException()
        }.getOrThrow()

    @Transactional
    fun unlike(likeRequest: LikeRequest, userId: Long): LikeResponse =
        run {
            deleteLikeOnRedis(likeRequest, userId)

            likeRepository.findByIdOrNull(likeRequest.id)
                ?.delete()
                ?: throw NoDataException()
            LikeResponse(true)
        }

    private fun deleteLikeOnRedis(likeRequest: LikeRequest, userId: Long) =
        runCatching {
            redisTemplate.opsForZSet()
                .remove(
                    "likes:${likeRequest.feedId}",
                    userId.toString(),
                )
        }.onFailure {
            throw RedisException()
        }
}