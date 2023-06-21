package com.flata.instagram.domain.feed.controller.dto

import com.flata.instagram.domain.file.controller.dto.FileResponse
import java.time.LocalDateTime

data class FeedResponse(
    val content: String,
    val createdAt: LocalDateTime,
    val files: List<FileResponse>
)
