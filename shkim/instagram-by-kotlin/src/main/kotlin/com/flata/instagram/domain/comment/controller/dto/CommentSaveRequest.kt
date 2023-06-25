package com.flata.instagram.domain.comment.controller.dto

data class CommentSaveRequest(
    val feedId: Long,
    val content: String
)