package com.flata.instagram.domain.comment.validation.validator

import com.flata.instagram.domain.comment.controller.dto.CommentDeletionRequest
import com.flata.instagram.domain.comment.service.CommentService
import com.flata.instagram.domain.comment.validation.annotation.CommentOwnerConstraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class CommentOwnerValidator(
    private val commentService: CommentService
) : ConstraintValidator<CommentOwnerConstraint, CommentDeletionRequest> {

    override fun isValid(request: CommentDeletionRequest, context: ConstraintValidatorContext?): Boolean {
        return commentService.findCommentById(request.commentId).user.isSame(request.userId)
    }
}