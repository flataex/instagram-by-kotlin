package com.flata.instagram.domain.follow.model.dto

import javax.validation.constraints.NotNull

data class FollowRequest(
    @field:NotNull
    val toUserId: Long
)