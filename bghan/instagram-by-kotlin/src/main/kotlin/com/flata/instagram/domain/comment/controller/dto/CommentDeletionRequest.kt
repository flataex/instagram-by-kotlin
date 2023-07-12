package com.flata.instagram.domain.comment.controller.dto

import com.flata.instagram.domain.comment.validation.annotation.CommentOwnerConstraint

@CommentOwnerConstraint
data class CommentDeletionRequest(
    val userId: Long,
    val commentId: Long
)
