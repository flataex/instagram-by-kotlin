package com.flata.instagram.domain.reply.controller.dto

data class ReplySaveRequest(
    val commentId: Long,
    val content: String
)