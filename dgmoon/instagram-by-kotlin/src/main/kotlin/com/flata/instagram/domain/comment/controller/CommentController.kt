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
    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): ResponseEntity<CommentResponse> =
        ResponseEntity.ok(
            commentService.getComment(id)
        )

    @PostMapping
    fun saveComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> =
        commentService.saveComment(commentRequest)
            .let {
                ResponseEntity.created(
                    URI.create("/comments$it")
                ).build()
            }

    @PutMapping
    fun updateComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> =
        run {
            commentService.updateComment(commentRequest)
            ResponseEntity.noContent().build()
        }

    @DeleteMapping
    fun deleteComment(@Valid @RequestBody commentRequest: CommentRequest): ResponseEntity<Unit> =
        run {
            commentService.deleteComment(commentRequest)
            ResponseEntity.noContent().build()
        }
}