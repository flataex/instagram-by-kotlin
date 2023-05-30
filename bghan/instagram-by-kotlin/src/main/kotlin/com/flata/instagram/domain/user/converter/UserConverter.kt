package com.flata.instagram.domain.user.converter

import com.flata.instagram.domain.user.controller.dto.SignInResponse
import com.flata.instagram.domain.user.controller.dto.SignUpRequest
import com.flata.instagram.domain.user.controller.dto.UserResponse
import com.flata.instagram.domain.user.model.User

fun SignUpRequest.toUser() = User(
    email = this.email,
    password = this.password,
    nickname = this.nickname
)

fun User.toSignInResponse() = SignInResponse(
    userId = this.id,
    password = this.password
)

fun User.toDTO() = UserResponse(
    userId = this.id,
    email = this.email,
    nickName = this.nickname,
    createdAt = this.createdAt
)