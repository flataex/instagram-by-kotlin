package com.flata.instagram.domain.feed.dto

import com.flata.instagram.domain.feed.model.Feed
import org.jetbrains.annotations.NotNull

class FeedRequest(
    val id: Long,
    @NotNull
    val userId: Long,
    @NotNull
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