package com.flata.instagram.domain.feed.converter

import com.flata.instagram.domain.feed.controller.dto.FeedResponse
import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.user.converter.toDTO

fun Feed.toDTOWith(fileUrls: List<String>): FeedResponse = FeedResponse(
    feedId = this.id,
    userResponse = this.user.toDTO(),
    content = this.content,
    fileUrls = fileUrls,
    createdAt = this.createdAt
)