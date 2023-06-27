package com.flata.instagram.domain.reply.model.dto

data class ReplyRequest(
    val commentId: Long,
    val content: String
)