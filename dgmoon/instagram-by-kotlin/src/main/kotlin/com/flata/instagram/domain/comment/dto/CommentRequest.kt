package com.flata.instagram.domain.comment.dto

import com.flata.instagram.domain.comment.model.Comment
import javax.validation.constraints.NotEmpty

data class CommentRequest(
    val id: Long,
    @field: NotEmpty
    val userId: Long,
    @field: NotEmpty
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