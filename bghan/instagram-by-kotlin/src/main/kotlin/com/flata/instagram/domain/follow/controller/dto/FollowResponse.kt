package com.flata.instagram.domain.follow.controller.dto

import com.flata.instagram.domain.user.controller.dto.UserResponse

data class FollowResponse(
    val from: UserResponse,
    val to: List<UserResponse>
)