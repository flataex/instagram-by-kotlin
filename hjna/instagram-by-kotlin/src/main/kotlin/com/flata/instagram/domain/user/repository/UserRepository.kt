package com.flata.instagram.domain.user.repository

import com.flata.instagram.domain.user.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
    fun findByEmail(email: String): Optional<User>
}