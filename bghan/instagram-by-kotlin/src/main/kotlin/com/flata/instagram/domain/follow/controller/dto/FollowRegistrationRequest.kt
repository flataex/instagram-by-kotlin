package com.flata.instagram.domain.follow.controller.dto

import com.flata.instagram.domain.follow.validation.annotation.DuplicateFollowConstraint
import com.flata.instagram.domain.follow.validation.annotation.FollowMySelfConstraint

@DuplicateFollowConstraint
@FollowMySelfConstraint
data class FollowRegistrationRequest(
    val fromUserId: Long,
    val toUserId: Long
)