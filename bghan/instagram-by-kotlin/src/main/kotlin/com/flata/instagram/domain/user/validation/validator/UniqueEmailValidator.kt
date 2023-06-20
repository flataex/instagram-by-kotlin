package com.flata.instagram.domain.user.validation.validator

import com.flata.instagram.domain.user.service.UserService
import com.flata.instagram.domain.user.validation.annotation.UniqueEmailConstraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

class UniqueEmailValidator(
    private val userService: UserService
) : ConstraintValidator<UniqueEmailConstraint, String> {

    override fun isValid(email: String, context: ConstraintValidatorContext?): Boolean =
        !userService.existsByEmail(email)
}