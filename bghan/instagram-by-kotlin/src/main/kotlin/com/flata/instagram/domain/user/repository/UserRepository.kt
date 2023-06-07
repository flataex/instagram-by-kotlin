package com.flata.instagram.domain.user.repository

import com.flata.instagram.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): User?
    fun existsByNickname(nickname: String): Boolean
}