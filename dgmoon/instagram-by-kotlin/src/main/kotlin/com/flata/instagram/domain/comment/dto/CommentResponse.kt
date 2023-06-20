package com.flata.instagram.domain.comment.dto

import com.flata.instagram.domain.comment.model.Reply

data class CommentResponse(
    val id: Long,
    val feedId: Long,
    val userId: Long,
    val content: String,
    val replies: List<Reply>
)