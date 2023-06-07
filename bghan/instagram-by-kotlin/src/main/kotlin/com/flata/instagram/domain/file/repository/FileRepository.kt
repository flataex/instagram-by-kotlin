package com.flata.instagram.domain.file.repository

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.file.model.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository: JpaRepository<File, Long> {
    fun findByFeed(feed: Feed): List<File>
}