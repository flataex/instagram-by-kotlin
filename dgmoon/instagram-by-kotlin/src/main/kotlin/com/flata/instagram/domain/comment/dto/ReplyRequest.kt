package com.flata.instagram.domain.comment.dto

import com.flata.instagram.domain.comment.model.Reply
import javax.validation.constraints.NotNull

data class ReplyRequest(
    val id: Long,
    @field: NotNull
    val userId: Long,
    @field: NotNull
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