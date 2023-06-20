package com.flata.instagram.domain.comment.dto

data class ReplyResponse(
    val id: Long,
    val userId: Long,
    val feedId: Long,
    val commentId: Long,
    val content: String
)