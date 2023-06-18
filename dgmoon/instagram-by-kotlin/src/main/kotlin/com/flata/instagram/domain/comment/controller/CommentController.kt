package com.flata.instagram.domain.comment.controller

import com.flata.instagram.domain.comment.dto.CommentRequest
import com.flata.instagram.domain.comment.dto.CommentResponse
import com.flata.instagram.domain.comment.service.CommentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/comments")
class CommentController(
    private val commentService: CommentService
) {
    @GetMapping
    fun getComments(): ResponseEntity<List<CommentResponse>> =
        ResponseEntity.ok(commentService.getComments())

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): ResponseEntity<CommentResponse> =
        ResponseEntity.ok(commentService.getComment(id))


    @PostMapping
    fun saveComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> =
        ResponseEntity.created(
            commentService.saveComment(commentRequest).let {
                URI.create("/comments/".plus(it))
            }
        ).build()

    @PutMapping
    fun updateComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> {
        commentService.updateComment(commentRequest)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    fun deleteComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> {
        commentService.deleteComment(commentRequest)
        return ResponseEntity.noContent().build()
    }
}