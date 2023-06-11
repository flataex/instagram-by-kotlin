package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.dto.FeedRequest
import com.flata.instagram.domain.feed.dto.FeedResponse
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FeedService(
    private val feedRepository: FeedRepository
) {
    @Transactional(readOnly = true)
    fun getFeeds(): List<FeedResponse> =
        feedRepository.findAll()
            .map { feed ->
                FeedResponse(
                    feed.id,
                    feed.userId,
                    feed.text
                )
            }

    @Transactional(readOnly = true)
    fun getFeed(id: Long): FeedResponse {
        val feed = feedRepository.findByIdOrNull(id) ?: throw NoDataException()
        return FeedResponse(
            feed.id,
            feed.userId,
            feed.text
        )
    }

    @Transactional
    fun saveFeed(feedRequest: FeedRequest): Long =
        feedRepository.save(feedRequest.toEntity()).id

    @Transactional
    fun deleteFeed(feedRequest: FeedRequest) {
        val feed = feedRepository.findByIdOrNull(feedRequest.id) ?: throw NoDataException()
        feed.delete()
    }
}