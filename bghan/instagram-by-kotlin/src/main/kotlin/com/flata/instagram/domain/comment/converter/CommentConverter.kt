package com.flata.instagram.domain.comment.converter

import com.flata.instagram.domain.comment.controller.dto.CommentDTO
import com.flata.instagram.domain.comment.controller.dto.ReplyDTO
import com.flata.instagram.domain.comment.model.Comment
import com.flata.instagram.domain.comment.model.Reply

fun CommentDTO.toEntity(): Comment = Comment(
    user = this.user,
    feed = this.feed,
    content = this.content
)

fun ReplyDTO.toEntity(): Reply = Reply(
    user = this.user,
    comment = this.comment,
    content = this.content
)