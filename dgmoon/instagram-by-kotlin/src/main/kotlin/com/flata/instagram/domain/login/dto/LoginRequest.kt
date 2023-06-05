package com.flata.instagram.domain.login.dto

import javax.validation.constraints.NotEmpty

class LoginRequest(
    @field:NotEmpty
    val email: String,
    @field:NotEmpty
    val password: String
)