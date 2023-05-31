package com.flata.instagram.domain.login.dto

import javax.validation.constraints.NotEmpty

class LoginRequest(
    @NotEmpty
    val email: String,
    @NotEmpty
    val password: String
)