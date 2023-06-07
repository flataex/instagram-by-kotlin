package com.flata.instagram.domain.login.dto

import javax.validation.constraints.NotEmpty

data class LoginRequest(
    @field:NotEmpty
    val email: String,
    @field:NotEmpty
    val password: String
)