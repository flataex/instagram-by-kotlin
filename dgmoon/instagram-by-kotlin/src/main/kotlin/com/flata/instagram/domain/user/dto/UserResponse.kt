package com.flata.instagram.domain.user.dto

data class UserResponse(
    val id: Long,
    val email: String,
    val password: String,
    val nickname: String
)
