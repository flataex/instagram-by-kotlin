package com.flata.instagram.domain.follow.validation.annotation

import com.flata.instagram.domain.follow.validation.validator.DuplicateFollowValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [DuplicateFollowValidator::class])
annotation class DuplicateFollowConstraint(
    val message: String = "이미 팔로우 되어 있는 상태입니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)