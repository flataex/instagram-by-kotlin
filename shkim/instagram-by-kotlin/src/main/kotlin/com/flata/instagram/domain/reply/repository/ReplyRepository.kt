package com.flata.instagram.domain.reply.repository

import com.flata.instagram.domain.reply.model.Reply
import org.springframework.data.jpa.repository.JpaRepository

interface ReplyRepository: JpaRepository<Reply, Long> {
}