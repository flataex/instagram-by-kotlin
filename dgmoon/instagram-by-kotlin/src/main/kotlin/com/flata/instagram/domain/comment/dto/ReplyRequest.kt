package com.flata.instagram.domain.comment.dto

import com.flata.instagram.domain.comment.model.Reply
import javax.validation.constraints.NotEmpty

data class ReplyRequest(
    val id: Long,
    @field: NotEmpty
    val userId: Long,
    @field: NotEmpty
    val commentId: Long,
    val content: String
) {
    fun toEntity(): Reply =
        Reply(
            id,
            userId,
            commentId,
            content,
            null
        )
}