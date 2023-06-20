package com.flata.instagram.domain.follow.validation.validator

import com.flata.instagram.domain.follow.controller.dto.FollowRegistrationRequest
import com.flata.instagram.domain.follow.service.FollowService
import com.flata.instagram.domain.follow.validation.annotation.DuplicateFollowConstraint
import com.flata.instagram.domain.user.service.UserService
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DuplicateFollowValidator(
    private val userService: UserService,
    private val followService: FollowService
) : ConstraintValidator<DuplicateFollowConstraint, FollowRegistrationRequest> {

    override fun isValid(followRegistrationRequest: FollowRegistrationRequest, context: ConstraintValidatorContext?): Boolean=
        !followService.existsFollowPair(
            fromUser = userService.findBy(followRegistrationRequest.fromUserId),
            toUser = userService.findBy(followRegistrationRequest.toUserId)
        )
}