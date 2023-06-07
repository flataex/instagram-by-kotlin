package com.flata.instagram.domain.feed.repository

import com.flata.instagram.domain.feed.model.Feed
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository : JpaRepository<Feed, Long>