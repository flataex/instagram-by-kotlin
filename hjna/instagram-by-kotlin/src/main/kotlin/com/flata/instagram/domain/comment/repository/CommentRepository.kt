package com.flata.instagram.domain.comment.repository

import com.flata.instagram.domain.comment.model.entity.Comment
import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
    fun findByFeed(feed: Feed): List<Comment>
    fun findByUserAndId(user: User, id: Long): Comment?
}