package com.flata.instagram.domain.like.repository

import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.like.model.entity.Like
import com.flata.instagram.domain.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository: JpaRepository<Like, Long> {
    fun findByUserAndFeed(user: User, feed: Feed): Like?

    fun findByFeed(feed: Feed): List<Like>
}