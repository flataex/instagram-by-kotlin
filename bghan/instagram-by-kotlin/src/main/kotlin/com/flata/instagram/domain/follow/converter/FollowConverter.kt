package com.flata.instagram.domain.follow.converter

import com.flata.instagram.domain.follow.controller.dto.FollowResponse
import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.domain.user.converter.toDTO

fun List<Follow>.toResponse(): FollowResponse? {
    return this.groupBy(
        keySelector = { it.fromUser },
        valueTransform = { it.toUser.toDTO() }
        )
        .map { FollowResponse(
            from = it.key.toDTO(),
            to = it.value
        ) }.firstOrNull()

}