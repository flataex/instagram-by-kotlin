package com.flata.instagram.domain.comment.controller.dto

import com.flata.instagram.domain.comment.validation.annotation.ReplyOwnerConstraint

@ReplyOwnerConstraint
data class ReplyDeletionRequest(
    val userId: Long,
    val replyId: Long
)
