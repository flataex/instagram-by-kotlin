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
    fun getFeeds(): List<FeedResponse> =
        feedRepository.findAll()
            .stream()
            .map { feed ->
                FeedResponse(
                    feed.id,
                    feed.userId,
                    feed.text
                )
            }.toList()

    @Transactional(readOnly = true)
    fun getFeed(id: Long): FeedResponse =
        feedRepository.findById(id)
            .orElseThrow {
                throw NoDataException()
            }.let {
                FeedResponse(
                    it.id,
                    it.userId,
                    it.text
                )
            }

    @Transactional
    fun saveFeed(feedRequest: FeedRequest): Long =
        feedRepository.save(feedRequest.toEntity()).id

    @Transactional
    fun deleteFeed(feedRequest: FeedRequest) =
        feedRepository.findById(feedRequest.id)
            .orElseThrow { throw NoDataException() }
            .delete()
}