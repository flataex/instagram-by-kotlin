package com.flata.instagram.domain.reply.controller

import com.flata.instagram.domain.reply.model.dto.ReplyRequest
import com.flata.instagram.domain.reply.model.dto.ReplyResponse
import com.flata.instagram.domain.reply.service.ReplyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/api/reply")
class ReplyController(private val replyService: ReplyService) {
    @PostMapping
    fun createReply(@Valid replyRequest: ReplyRequest, session: HttpSession): ResponseEntity<Void> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        replyService.createReply(jwt, replyRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @GetMapping("/{commentId}")
    fun findByCommentId(@PathVariable(value = "commentId") commentId: Long): ResponseEntity<List<ReplyResponse>> {
        return ResponseEntity(replyService.findByCommentId(commentId), HttpStatus.OK)
    }

    @DeleteMapping("/{replyId}")
    fun deleteReply(@PathVariable(value = "replyId") replyId: Long, session: HttpSession): ResponseEntity<Void> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        replyService.deleteReply(jwt, replyId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @PutMapping("/{replyId}")
    fun updateReply(@PathVariable(value = "replyId") replyId: Long, @Valid replyRequest: ReplyRequest, session: HttpSession): ResponseEntity<ReplyResponse> {
        val jwt = session.getAttribute("USER_TOKEN").toString()
        val replyResponse = replyService.updateReply(jwt, replyId, replyRequest)
        return ResponseEntity(replyResponse, HttpStatus.OK)
    }
}
