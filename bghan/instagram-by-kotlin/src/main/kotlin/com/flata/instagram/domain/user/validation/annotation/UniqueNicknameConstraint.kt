package com.flata.instagram.domain.user.validation.annotation

import com.flata.instagram.domain.user.validation.validator.UniqueNicknameValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueNicknameValidator::class])
annotation class UniqueNicknameConstraint(
    val message: String = "이미 등록된 닉네임이 존재합니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)