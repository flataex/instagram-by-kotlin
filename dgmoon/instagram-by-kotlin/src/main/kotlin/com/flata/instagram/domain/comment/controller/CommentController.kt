package com.flata.instagram.domain.comment.controller

import com.flata.instagram.domain.comment.dto.CommentRequest
import com.flata.instagram.domain.comment.dto.CommentResponse
import com.flata.instagram.domain.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/comments")
class CommentController(
    private val commentService: CommentService
) {
    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): ResponseEntity<CommentResponse> =
        commentService.getComment(id)

    @PostMapping
    fun saveComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> =
        commentService.saveComment(commentRequest)

    @PutMapping
    fun updateComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> =
        commentService.updateComment(commentRequest)

    @DeleteMapping
    fun deleteComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> =
        commentService.deleteComment(commentRequest)
}