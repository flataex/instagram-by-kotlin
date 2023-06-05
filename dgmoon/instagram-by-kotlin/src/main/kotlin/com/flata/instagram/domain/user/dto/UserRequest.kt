package com.flata.instagram.domain.user.dto

import com.flata.instagram.domain.user.model.Users
import org.springframework.security.crypto.bcrypt.BCrypt
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class UserRequest(
    val id: Long,
    @field:NotEmpty
    @field:Email
    val email: String,
    @field:NotEmpty
    val password: String,
    @field:NotEmpty
    val nickname: String
) {
    fun toEntity(): Users {
        return Users(
            this.id,
            this.email,
            BCrypt.hashpw(this.password, BCrypt.gensalt()),
            this.nickname
        )
    }
}