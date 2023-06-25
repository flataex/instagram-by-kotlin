package com.flata.instagram.domain.comment.controller

import com.flata.instagram.domain.comment.controller.dto.CommentDeletionRequest
import com.flata.instagram.domain.comment.controller.dto.CommentRegistrationRequest
import com.flata.instagram.domain.comment.controller.dto.ReplyDeletionRequest
import com.flata.instagram.domain.comment.facade.CommentFacade
import com.flata.instagram.global.model.Authorization
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comments")
class CommentController(
    private val commentFacade: CommentFacade
) {

    @PostMapping("/{feedId}")
    fun saveComment(
        @Authorization userId: Long,
        @PathVariable(value = "feedId") feedId: Long,
        @RequestBody request: CommentRegistrationRequest
    ): ResponseEntity<Void> {
        commentFacade.saveComment(userId, feedId, request.content)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PostMapping("/reply/{commentId}")
    fun saveReply(
        @Authorization userId: Long,
        @PathVariable(value = "commentId") commentId: Long,
        @RequestBody request: CommentRegistrationRequest
    ): ResponseEntity<Void> {
        commentFacade.saveReply(userId, commentId, request.content)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @Authorization userId: Long,
        @PathVariable(value = "commentId") commentId: Long,
    ): ResponseEntity<Void> {
        commentFacade.deleteComment(
            CommentDeletionRequest(
                userId = userId,
                commentId = commentId
            )
        )
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{replyId}")
    fun deleteReply(
        @Authorization userId: Long,
        @PathVariable(value = "replyId") replyId: Long,
    ): ResponseEntity<Void> {
        commentFacade.deleteReply(
            ReplyDeletionRequest(
                userId = userId,
                replyId = replyId
            )
        )
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}