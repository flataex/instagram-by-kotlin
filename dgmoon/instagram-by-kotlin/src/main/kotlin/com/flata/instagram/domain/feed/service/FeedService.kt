package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.dto.FeedRequest
import com.flata.instagram.domain.feed.dto.FeedResponse
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.net.URI

@Service
class FeedService(
    private val feedRepository: FeedRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {
    @Transactional(readOnly = true)
    fun getFeeds(pageable: Pageable): ResponseEntity<List<FeedResponse>> =
        feedRepository.findAll(pageable)
            .map { feed ->
                FeedResponse(
                    feed.id,
                    feed.userId,
                    feed.content,
                    feed.comments ?: mutableListOf(),
                    feed.likes?.size?.toLong() ?: 0
                )
            }.let {
                ResponseEntity.ok(it.toList())
            }

    @Transactional(readOnly = true)
    fun getFeed(id: Long): ResponseEntity<FeedResponse> =
        feedRepository.findByIdOrNull(id)
            ?.let {
                ResponseEntity.ok(
                    FeedResponse(
                        it.id,
                        it.userId,
                        it.content,
                        it.comments ?: mutableListOf(),
                        getNumberOfLikes(it.id)
                    )
                )
            }
            ?: throw NoDataException()

    private fun getNumberOfLikes(feedId: Long): Long =
        redisTemplate.opsForZSet()
            .zCard(
                "likes:".plus(feedId)
            )
            ?: 0

    @Transactional
    fun saveFeed(feedRequest: FeedRequest): ResponseEntity<Unit> =
        run {
            ResponseEntity.created(
                URI.create(
                    "/feeds/".plus(
                        feedRepository.save(feedRequest.toEntity()).id
                    )
                )
            ).build()
        }

    @Transactional
    fun deleteFeed(feedRequest: FeedRequest): ResponseEntity<Unit> =
        feedRepository.findByIdOrNull(feedRequest.id)
            ?.let {
                it.delete()
                ResponseEntity.noContent().build()
            }
            ?: throw NoDataException()
}