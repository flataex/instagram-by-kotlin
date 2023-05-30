package com.flata.instagram.domain.user.model.dto

data class UserRegisterRequest(
        val email: String,
        val password: String,
        val nickname: String
)