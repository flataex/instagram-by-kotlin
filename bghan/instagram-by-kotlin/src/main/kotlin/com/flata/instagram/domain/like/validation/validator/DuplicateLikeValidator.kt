package com.flata.instagram.domain.like.validation.validator

import com.flata.instagram.domain.like.controller.dto.LikeRequest
import com.flata.instagram.domain.like.service.LikeService
import com.flata.instagram.domain.like.validation.annotation.DuplicateLikeConstraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class DuplicateLikeValidator(
    private val likeService: LikeService
) : ConstraintValidator<DuplicateLikeConstraint, LikeRequest> {

    override fun isValid(request: LikeRequest, context: ConstraintValidatorContext?): Boolean =
        !likeService.existsBy(request.userId, request.feedId)
}