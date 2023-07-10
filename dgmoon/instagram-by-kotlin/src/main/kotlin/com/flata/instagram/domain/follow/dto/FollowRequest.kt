package com.flata.instagram.domain.follow.dto

import javax.validation.constraints.NotNull

data class FollowRequest(
    val id: Long,
    @field:NotNull
    val toUserId: Long
)