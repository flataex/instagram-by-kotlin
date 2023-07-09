package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.dto.FeedRequest
import com.flata.instagram.domain.feed.dto.FeedResponse
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.domain.Pageable
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import javax.validation.Valid

@Service
class FeedService(
    private val feedRepository: FeedRepository,
    private val redisTemplate: RedisTemplate<String, String>
) {
    @Transactional(readOnly = true)
    fun getFeeds(pageable: Pageable): List<FeedResponse> =
        feedRepository.findAll(pageable)
            .filter {
                it.deletedAt == null
            }
            .map { feed ->
                FeedResponse(
                    feed.id,
                    feed.userId,
                    feed.content,
                    feed.comments ?: mutableListOf(),
                    feed.likes?.size?.toLong() ?: 0
                )
            }.toList()

    @Transactional(readOnly = true)
    fun getFeed(id: Long): FeedResponse =
        feedRepository.findByIdOrNull(id)
            ?.let {
                FeedResponse(
                    it.id,
                    it.userId,
                    it.content,
                    it.comments ?: mutableListOf(),
                    getNumberOfLikes(it.id)
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
    fun saveFeed(
        @Valid @RequestBody feedRequest: FeedRequest,
        userId: Long
    ): Long =
        feedRepository.save(feedRequest.toEntityWith(userId)).id

    @Transactional
    fun deleteFeed(@Valid @RequestBody feedRequest: FeedRequest) =
        feedRepository.findByIdOrNull(feedRequest.id)
            ?.delete()
            ?: throw NoDataException()
}