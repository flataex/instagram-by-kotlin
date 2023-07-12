package com.flata.instagram.domain.follow.repository

import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository: JpaRepository<Follow, Long> {
    fun existsByFromUserAndToUser(fromUser: User, toUser: User): Boolean
    fun findByFromUserId(userId: Long): List<Follow>
    fun findByFromUserIdAndToUserId(fromUserId: Long, toUserId: Long): Follow?
}