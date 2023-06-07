package com.flata.instagram.domain.user.controller.dto

data class UserSaveRequest(
    val email: String,
    val password: String,
    val nickname: String
)