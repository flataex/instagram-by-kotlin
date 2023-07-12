package com.flata.instagram.domain.comment.controller.dto

import com.flata.instagram.domain.reply.controller.dto.ReplyResponse

data class CommentResponse(
    val commentId: Long,
    val nickName: String,
    val content: String,
    val replies: List<ReplyResponse>
)