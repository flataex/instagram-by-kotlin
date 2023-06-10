package com.flata.instagram.domain.login.dto

import javax.validation.constraints.NotEmpty

data class LoginRequest(
    @field:NotEmpty
    var email: String,
    @field:NotEmpty
    var password: String
)