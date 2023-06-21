package com.flata.instagram.domain.user.controller.dto

import com.fasterxml.jackson.annotation.JsonIgnore

data class SignInResponse(
    val userId: Long,
    @JsonIgnore
    val password: String
)
