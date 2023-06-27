package com.flata.instagram.domain.follow.repository

import com.flata.instagram.domain.follow.model.entity.Follow
import com.flata.instagram.domain.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository: JpaRepository<Follow, Long> {
    fun findByFromUserIdAndToUserId(fromUser: Long, toUser: Long): Follow?

    fun findByToUser(toUser: User): List<Follow>
}