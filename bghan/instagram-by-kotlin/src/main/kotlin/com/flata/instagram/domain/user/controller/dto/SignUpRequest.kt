package com.flata.instagram.domain.user.controller.dto

import com.flata.instagram.domain.user.validation.annotation.UniqueEmailConstraint
import com.flata.instagram.domain.user.validation.annotation.UniqueNicknameConstraint
import javax.validation.constraints.NotBlank

data class SignUpRequest(
    @field:NotBlank(message = "한글자 이상 입력해주세요.")
    @field:UniqueEmailConstraint
    val email: String,

    @field:NotBlank(message = "한글자 이상 입력해주세요.")
    val password: String,

    @field:NotBlank(message = "한글자 이상 입력해주세요.")
    @field:UniqueNicknameConstraint
    val nickname: String
)