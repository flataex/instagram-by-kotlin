package com.flata.instagram.domain.feed.dto

import com.flata.instagram.domain.comment.model.Comment
import com.flata.instagram.domain.like.model.Like

data class FeedResponse(
    val id: Long,
    val userId: Long,
    val content: String,
    val comments: List<Comment>,
    val like: Long
)