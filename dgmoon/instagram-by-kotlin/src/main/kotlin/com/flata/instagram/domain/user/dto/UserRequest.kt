package com.flata.instagram.domain.user.dto

import com.flata.instagram.domain.user.model.User
import org.springframework.security.crypto.bcrypt.BCrypt
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class UserRequest(
    val id: Long,
    @field:NotEmpty
    @field:Email
    val email: String,
    @field:NotEmpty
    val password: String,
    @field:NotEmpty
    val nickname: String
) {
    fun toEntity(): User =
        User(
            this.id,
            this.email,
            BCrypt.hashpw(this.password, BCrypt.gensalt()),
            this.nickname
        )
}