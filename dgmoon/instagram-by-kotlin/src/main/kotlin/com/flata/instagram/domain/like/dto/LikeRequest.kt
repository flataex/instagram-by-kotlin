package com.flata.instagram.domain.like.dto

import javax.validation.constraints.NotNull

data class LikeRequest(
    val id: Long,
    @field:NotNull
    val feedId: Long
)