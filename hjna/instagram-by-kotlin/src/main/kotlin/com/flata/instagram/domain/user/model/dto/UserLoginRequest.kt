package com.flata.instagram.domain.user.model.dto

data class UserLoginRequest(
    val email: String,
    val password: String
)