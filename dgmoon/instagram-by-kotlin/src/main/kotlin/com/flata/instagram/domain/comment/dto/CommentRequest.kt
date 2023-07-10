package com.flata.instagram.domain.comment.dto

import com.flata.instagram.domain.comment.model.Comment
import javax.validation.constraints.NotNull

data class CommentRequest(
    val id: Long,
    @field: NotNull
    val userId: Long,
    @field: NotNull
    val feedId: Long,
    val content: String
) {
    fun toEntity(): Comment =
        Comment(
            id,
            userId,
            feedId,
            content,
            null,
        )
}