package com.flata.instagram.domain.user.repository

import com.flata.instagram.domain.user.model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<Users, Long> {
    fun findByEmail(email: String): Users
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
}