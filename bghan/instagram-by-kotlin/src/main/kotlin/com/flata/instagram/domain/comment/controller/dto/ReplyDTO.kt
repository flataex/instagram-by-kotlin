package com.flata.instagram.domain.comment.controller.dto

import com.flata.instagram.domain.comment.model.Comment
import com.flata.instagram.domain.user.model.User

data class ReplyDTO(
    val user: User,
    val comment: Comment,
    val content: String
)
