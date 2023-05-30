package com.flata.instagram.domain.feed.controller.dto

import com.flata.instagram.domain.feed.validation.annotation.FeedOwnerConstraint

@FeedOwnerConstraint
data class FeedDeletionRequest(
    val userId: Long,
    val feedId: Long
)