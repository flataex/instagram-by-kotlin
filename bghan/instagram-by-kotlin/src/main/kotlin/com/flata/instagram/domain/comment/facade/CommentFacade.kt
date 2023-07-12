package com.flata.instagram.domain.comment.facade

import com.flata.instagram.domain.comment.controller.dto.CommentDTO
import com.flata.instagram.domain.comment.controller.dto.CommentDeletionRequest
import com.flata.instagram.domain.comment.controller.dto.ReplyDTO
import com.flata.instagram.domain.comment.controller.dto.ReplyDeletionRequest
import com.flata.instagram.domain.comment.model.Comment
import com.flata.instagram.domain.comment.service.CommentService
import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.feed.service.FeedService
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.domain.user.service.UserService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.validation.annotation.Validated
import javax.validation.Valid

@Component
@Transactional
@Validated
class CommentFacade(
    private val userService: UserService,
    private val feedService: FeedService,
    private val commentService: CommentService
) {
    fun saveComment(
        userId: Long,
        feedId: Long,
        content: String
    ) {
        val user: User = userService.findBy(userId)
        val feed: Feed = feedService.findById(feedId)

        commentService.saveComment(
            CommentDTO(
                user = user,
                feed = feed,
                content = content
            )
        )
    }

    fun saveReply(
        userId: Long,
        commentId: Long,
        content: String
    ) {
        val user: User = userService.findBy(userId)
        val comment: Comment = commentService.findCommentById(commentId)

        commentService.saveReply(
            ReplyDTO(
                user = user,
                comment = comment,
                content = content
            )
        )
    }

    fun deleteComment(
        @Valid request: CommentDeletionRequest
    ) {
        commentService.deleteComment(request.commentId)
    }

    fun deleteReply(
        @Valid request: ReplyDeletionRequest
    ) {
        commentService.deleteReply(request.replyId)
    }
}