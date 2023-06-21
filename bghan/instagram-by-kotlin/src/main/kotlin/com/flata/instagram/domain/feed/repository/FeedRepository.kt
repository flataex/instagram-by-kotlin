package com.flata.instagram.domain.feed.repository

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository: JpaRepository<Feed, Long> {
    fun findByUser(user: User): List<Feed>
}