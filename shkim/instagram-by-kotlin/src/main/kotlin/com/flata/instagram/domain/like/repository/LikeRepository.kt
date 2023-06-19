package com.flata.instagram.domain.like.repository

import com.flata.instagram.domain.like.model.Like
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository: JpaRepository<Like, Long> {
}