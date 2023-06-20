package com.flata.instagram.domain.reply.controller

import com.flata.instagram.domain.reply.controller.dto.ReplySaveRequest
import com.flata.instagram.domain.reply.service.ReplyService
import com.flata.instagram.global.model.LoginUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/replies")
class ReplyController(
    private val replyService: ReplyService
) {

    @PostMapping
    fun save(
        @LoginUser userId: Long,
        @RequestBody request: ReplySaveRequest
    ): ResponseEntity<Long> {
        return ResponseEntity(replyService.save(userId, request), HttpStatus.CREATED)
    }

    @DeleteMapping("/{id}")
    fun delete(
        @LoginUser userId: Long,
        @PathVariable(value = "id") replyId: Long
    ):ResponseEntity<Void> {
        replyService.delete(userId, replyId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}