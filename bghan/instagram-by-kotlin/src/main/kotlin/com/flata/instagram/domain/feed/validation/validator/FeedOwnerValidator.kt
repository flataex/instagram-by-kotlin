package com.flata.instagram.domain.feed.validation.validator

import com.flata.instagram.domain.feed.controller.dto.FeedDeletionRequest
import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.domain.feed.validation.annotation.FeedOwnerConstraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class FeedOwnerValidator(
    private val feedService: FeedService
) : ConstraintValidator<FeedOwnerConstraint, FeedDeletionRequest> {

    override fun isValid(request: FeedDeletionRequest, context: ConstraintValidatorContext?): Boolean {
        return feedService.findById(request.feedId).isOwner(request.userId)
    }
}