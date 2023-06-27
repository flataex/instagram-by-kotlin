package com.flata.instagram.domain.comment.model.dto

data class CommentRequest(
    val feedId: Long,
    val content: String
)
