package com.flata.instagram.domain.feed.dto

import com.flata.instagram.domain.feed.model.Feed

class FeedRequest(
    val id: Long,
    val userId: Long,
    val text: String
) {
    fun toEntity(): Feed {
        return Feed(
            id,
            userId,
            text
        )
    }
}