package com.flata.instagram.domain.like.validation.annotation

import com.flata.instagram.domain.like.validation.validator.DuplicateLikeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DuplicateLikeValidator::class])
annotation class DuplicateLikeConstraint(
    val message: String = "이미 좋아요 되어 있는 상태입니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)