package com.flata.instagram.domain.feed.model.dto

import javax.validation.constraints.NotBlank


data class FeedRequest(
    @field:NotBlank
    val content: String
)