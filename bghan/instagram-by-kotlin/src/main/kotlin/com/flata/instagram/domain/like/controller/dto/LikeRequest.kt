package com.flata.instagram.domain.like.controller.dto

import com.flata.instagram.domain.like.validation.annotation.DuplicateLikeConstraint

@DuplicateLikeConstraint
data class LikeRequest(
    val userId: Long,
    val feedId: Long
)
