package com.flata.instagram.domain.like.dto

import javax.validation.constraints.NotEmpty

data class LikeRequest(
    val id: Long,
    @field:NotEmpty
    val feedId: Long
)