package com.flata.instagram.domain.reply.repository

import com.flata.instagram.domain.comment.model.entity.Comment
import com.flata.instagram.domain.reply.model.entity.Reply
import com.flata.instagram.domain.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository: JpaRepository<Reply, Long> {
    fun findByComment(comment: Comment): List<Reply>
    fun findByUserAndId(user: User, id: Long): Reply?
}