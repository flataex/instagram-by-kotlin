package com.flata.instagram.domain.feed.service

import com.flata.instagram.domain.feed.dto.FeedRequest
import com.flata.instagram.domain.feed.dto.FeedResponse
import com.flata.instagram.domain.feed.repository.FeedRepository
import com.flata.instagram.global.exception.NoDataException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FeedService(
    private val feedRepository: FeedRepository
) {
    @Transactional(readOnly = true)
    fun getFeeds(): List<FeedResponse> {
        return feedRepository.findAll()
            .stream()
            .map { feed ->
                FeedResponse(
                    feed.id,
                    feed.userId,
                    feed.text
                )
            }.toList()
    }

    @Transactional(readOnly = true)
    fun getFeed(id: Long): FeedResponse {
        val feed = feedRepository.findById(id)
            .orElseThrow {
                throw NoDataException()
            }
        return FeedResponse(
            feed.id,
            feed.userId,
            feed.text
        )
    }

    @Transactional
    fun saveFeed(feedRequest: FeedRequest): Long {
        return feedRepository.save(feedRequest.toEntity()).id
    }

    @Transactional
    fun deleteFeed(feedRequest: FeedRequest) {
        val feedToDelete = feedRepository.findById(feedRequest.id)
            .orElseThrow { throw NoDataException() }
        feedToDelete.delete()
    }
}