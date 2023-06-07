package com.flata.instagram.domain.feed.dto

data class FeedResponse(
    val id: Long,
    val userId: Long,
    val text: String
)