package com.flata.instagram.domain.feed.controller.dto

import com.flata.instagram.domain.comment.controller.dto.CommentResponse
import com.flata.instagram.domain.file.controller.dto.FileResponse
import com.flata.instagram.domain.like.controller.dto.LikeResponse
import java.time.LocalDateTime

data class FeedResponse(
    val content: String,
    val createdAt: LocalDateTime,
    val comments: List<CommentResponse>,
    val likes: List<LikeResponse>,
    val files: List<FileResponse>
)
