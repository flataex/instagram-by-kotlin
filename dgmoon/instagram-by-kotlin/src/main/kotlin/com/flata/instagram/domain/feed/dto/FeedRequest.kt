package com.flata.instagram.domain.feed.dto

import com.flata.instagram.domain.feed.model.Feed
import org.jetbrains.annotations.NotNull

data class FeedRequest(
    val id: Long,
    @field:NotNull
    val text: String
) {
    fun toEntityWith(userId: Long): Feed =
        Feed(
            id,
            userId,
            text,
            null,
            null,
            null
        )
}