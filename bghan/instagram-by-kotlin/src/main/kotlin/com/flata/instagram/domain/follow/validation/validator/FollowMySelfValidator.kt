package com.flata.instagram.domain.follow.validation.validator

import com.flata.instagram.domain.follow.controller.dto.FollowRegistrationRequest
import com.flata.instagram.domain.follow.validation.annotation.FollowMySelfConstraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class FollowMySelfValidator
    : ConstraintValidator<FollowMySelfConstraint, FollowRegistrationRequest> {

    override fun isValid(followRegistrationRequest: FollowRegistrationRequest, context: ConstraintValidatorContext?): Boolean =
        followRegistrationRequest.fromUserId != followRegistrationRequest.toUserId
}