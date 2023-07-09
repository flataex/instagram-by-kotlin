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
        replyService.saveReply(replyRequest).let {
            ResponseEntity.created(
                URI.create("/replies/".plus(it))
            ).build()
        }

    @PutMapping
    fun updateReply(@Valid @RequestBody replyRequest: ReplyRequest): ResponseEntity<Unit> =
        run {
            replyService.updateReply(replyRequest)
            ResponseEntity.noContent().build()
        }

    @DeleteMapping
    fun deleteReply(@Valid @RequestBody replyRequest: ReplyRequest): ResponseEntity<Unit> =
        run {
            replyService.deleteReply(replyRequest)
            ResponseEntity.noContent().build()
        }
}