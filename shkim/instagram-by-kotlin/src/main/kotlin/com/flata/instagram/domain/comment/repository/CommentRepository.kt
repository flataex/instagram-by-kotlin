package com.flata.instagram.domain.comment.repository

import com.flata.instagram.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}