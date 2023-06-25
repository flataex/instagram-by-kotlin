package com.flata.instagram.domain.comment.validation.validator

import com.flata.instagram.domain.comment.controller.dto.ReplyDeletionRequest
import com.flata.instagram.domain.comment.service.CommentService
import com.flata.instagram.domain.comment.validation.annotation.CommentOwnerConstraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class ReplyOwnerValidator(
    private val commentService: CommentService
) : ConstraintValidator<CommentOwnerConstraint, ReplyDeletionRequest> {

    override fun isValid(request: ReplyDeletionRequest, context: ConstraintValidatorContext?): Boolean {
        return commentService.findCommentById(request.replyId).user.isSame(request.userId)
    }
}