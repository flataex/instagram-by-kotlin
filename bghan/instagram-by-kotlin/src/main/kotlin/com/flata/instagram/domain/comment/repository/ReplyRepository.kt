package com.flata.instagram.domain.comment.repository

import com.flata.instagram.domain.comment.model.Reply
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository: JpaRepository<Reply, Long> {
    fun findByCommentId(commentId: Long): List<Reply>
}