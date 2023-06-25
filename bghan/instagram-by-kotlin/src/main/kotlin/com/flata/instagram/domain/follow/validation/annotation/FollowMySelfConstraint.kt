package com.flata.instagram.domain.follow.validation.annotation

import com.flata.instagram.domain.follow.validation.validator.FollowMySelfValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [FollowMySelfValidator::class])
annotation class FollowMySelfConstraint(
    val message: String = "자기 자신은 팔로우할 수 없습니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)