package com.flata.instagram.domain.user.controller.dto

data class UserLoginRequest(
    val email: String,
    val password: String
)