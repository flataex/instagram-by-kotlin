package com.flata.instagram.domain.comment.controller.dto

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.user.model.User

data class CommentDTO(
    val user: User,
    val feed: Feed,
    val content: String
)
