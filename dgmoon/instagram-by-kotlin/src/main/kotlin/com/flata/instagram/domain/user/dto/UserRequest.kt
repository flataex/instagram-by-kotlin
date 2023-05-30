package com.flata.instagram.domain.user.dto

import com.flata.instagram.domain.user.model.Users
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class UserRequest(
    val id: Long,
    @NotEmpty
    @Email
    val email: String,
    @NotEmpty
    val password: String,
    @NotEmpty
    val nickname: String
) {
    fun toEntity(): Users {
        return Users(
            this.id,
            this.email,
            this.password,
            this.nickname
        )
    }
}