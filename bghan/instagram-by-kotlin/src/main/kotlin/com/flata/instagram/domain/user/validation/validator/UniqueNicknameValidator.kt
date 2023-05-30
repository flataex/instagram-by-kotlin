package com.flata.instagram.domain.user.validation.validator

import com.flata.instagram.domain.user.service.UserService
import com.flata.instagram.domain.user.validation.annotation.UniqueNicknameConstraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UniqueNicknameValidator(
    private val userService: UserService
) : ConstraintValidator<UniqueNicknameConstraint, String> {

    override fun isValid(nickName: String, context: ConstraintValidatorContext?): Boolean =
        !userService.existsByNickname(nickName)
}