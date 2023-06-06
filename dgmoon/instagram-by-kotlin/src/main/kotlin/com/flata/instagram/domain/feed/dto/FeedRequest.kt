package com.flata.instagram.domain.feed.dto

import com.flata.instagram.domain.feed.model.Feed
import org.jetbrains.annotations.NotNull

data class FeedRequest(
    val id: Long,
    @field:NotNull
    val userId: Long,
    @field:NotNull
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