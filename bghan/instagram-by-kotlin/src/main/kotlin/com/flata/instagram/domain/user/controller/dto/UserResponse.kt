package com.flata.instagram.domain.user.controller.dto

import java.time.LocalDateTime

data class UserResponse(
    val userId: Long,
    val nickName: String,
    val email: String,
    val createdAt: LocalDateTime
)
