package com.flata.instagram.domain.user.validation.annotation

import com.flata.instagram.domain.user.validation.validator.UniqueEmailValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueEmailValidator::class])
annotation class UniqueEmailConstraint(
    val message: String = "이미 등록된 이메일이 존재합니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
