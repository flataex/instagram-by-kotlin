package com.flata.instagram.domain.like.service

import com.flata.instagram.domain.like.dto.LikeRequest
import com.flata.instagram.domain.like.model.Like
import com.flata.instagram.domain.like.repository.LikeRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.LocalDateTime

@Service
class LikeService(
    private val likeRepository: LikeRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun like(likeRequest: LikeRequest, userId: Long): Boolean {
        try {
            redisTemplate.opsForZSet()
                .add(
                    "likes:".plus(likeRequest.feedId),
                    userId.toString(),
                    Timestamp.valueOf(LocalDateTime.now()).time.toDouble()
                )

            likeRepository.save(
                Like(
                    0,
                    userId,
                    likeRequest.feedId
                )
            )
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun unlike(likeRequest: LikeRequest, userId: Long): Boolean {
        try {
            redisTemplate.opsForZSet()
                .remove(
                    "likes:".plus(likeRequest.feedId),
                    userId.toString(),
                )

            val like = likeRepository.findByIdOrNull(likeRequest.id) ?: throw NoDataException()

            like.delete()

            return true
        } catch (e: Exception) {
            return false
        }
    }
}