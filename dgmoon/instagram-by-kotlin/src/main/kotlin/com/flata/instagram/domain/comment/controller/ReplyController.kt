package com.flata.instagram.domain.comment.controller

import com.flata.instagram.domain.comment.dto.ReplyRequest
import com.flata.instagram.domain.comment.service.ReplyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/replies")
class ReplyController(
    private val replyService: ReplyService
) {
    @PostMapping
    fun saveReply(@Valid @RequestBody replyRequest: ReplyRequest): ResponseEntity<Unit> =
        ResponseEntity.created(
            replyService.saveReply(replyRequest).let {
                URI.create("/replies/".plus(it))
            }
        ).build()

    @PutMapping
    fun updateReply(@Valid @RequestBody replyRequest: ReplyRequest): ResponseEntity<Unit> {
        replyService.updateReply(replyRequest)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping
    fun deleteReply(@Valid @RequestBody replyRequest: ReplyRequest): ResponseEntity<Unit> {
        replyService.deleteReply(replyRequest)
        return ResponseEntity.noContent().build()
    }
}