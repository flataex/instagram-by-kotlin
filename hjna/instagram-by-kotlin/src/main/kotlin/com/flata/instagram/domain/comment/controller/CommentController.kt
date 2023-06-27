package com.flata.instagram.domain.comment.controller

import com.flata.instagram.domain.comment.model.dto.CommentRequest
import com.flata.instagram.domain.comment.model.dto.CommentResponse
import com.flata.instagram.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/api/comment")
class CommentController(private val commentService: CommentService) {


    @PostMapping
    fun createComment( @Valid commentRequest: CommentRequest,
                   session: HttpSession): ResponseEntity<Void> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        commentService.createComment(jwt, commentRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }


    @GetMapping("/{feedId}")
    fun findById(
        @PathVariable(value = "feedId") feedId: Long
    ): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity(commentService.findByFeedId(feedId), HttpStatus.OK)
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable(value = "commentId") commentId: Long,
        session: HttpSession
    ): ResponseEntity<Void> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        commentService.deleteComment(jwt, commentId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable(value = "commentId") feedId: Long,
        @Valid commentRequest: CommentRequest,
        session: HttpSession
    ): ResponseEntity<CommentResponse> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        val commentResponse = commentService.updateComment(jwt, feedId, commentRequest)
        return ResponseEntity(commentResponse, HttpStatus.OK)
    }
}