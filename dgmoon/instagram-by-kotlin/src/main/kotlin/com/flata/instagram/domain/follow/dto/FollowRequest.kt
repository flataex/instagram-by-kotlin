package com.flata.instagram.domain.follow.dto

import javax.validation.constraints.NotEmpty

data class FollowRequest(
    val id: Long,
    @field:NotEmpty
    val toUserId: Long
)