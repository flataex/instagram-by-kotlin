package com.flata.instagram.domain.feed.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.flata.instagram.domain.feed.model.entity.Feed

interface FeedRepository: JpaRepository<Feed, Long> {
}