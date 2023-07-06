package com.flata.instagram.domain.feed.model.dto

import com.flata.instagram.domain.feed.model.entity.Feed
import java.time.LocalDateTime

data class FeedResponse (
    val feedId: Long,
    val content: String,
    val userId: Long?,
    val fileName: List<String>,
    val createdAt: LocalDateTime
) {
    constructor(feed: Feed?) : this(
        feedId = feed?.id ?: throw IllegalArgumentException("Feed cannot be null"),
        content = feed.content,
        userId = feed.user.id,
        fileName = listOf(), // This should be replaced with appropriate logic
        createdAt = feed.createdAt
    )
}

