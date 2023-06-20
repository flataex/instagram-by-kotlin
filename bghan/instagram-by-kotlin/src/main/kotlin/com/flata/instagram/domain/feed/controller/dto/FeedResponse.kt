package com.flata.instagram.domain.feed.controller.dto

import com.flata.instagram.domain.user.controller.dto.UserResponse
import java.time.LocalDateTime

data class FeedResponse(
    val feedId: Long,
    val userResponse: UserResponse,
    val content: String,
    val fileUrls: List<String>,
    val createdAt: LocalDateTime
)
