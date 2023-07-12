package com.flata.instagram.domain.follow.repository

import com.flata.instagram.domain.follow.model.Follow
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository: JpaRepository<Follow, Long> {
}