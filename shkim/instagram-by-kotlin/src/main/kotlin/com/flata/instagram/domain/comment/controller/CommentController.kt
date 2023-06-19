package com.flata.instagram.domain.comment.controller

import com.flata.instagram.domain.comment.controller.dto.CommentSaveRequest
import com.flata.instagram.domain.comment.service.CommentService
import com.flata.instagram.global.model.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/comments")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun save(
        @LoginUser userId: Long,
        @RequestBody request: CommentSaveRequest
    ): ResponseEntity<Long> {
        return ResponseEntity(commentService.save(userId, request), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @LoginUser userId: Long,
        @PathVariable(value = "id") commentId: Long
    ): ResponseEntity<Void> {
        commentService.delete(userId, commentId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}