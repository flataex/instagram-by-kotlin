package com.flata.instagram.domain.comment.validation.annotation

import com.flata.instagram.domain.comment.validation.validator.ReplyOwnerValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [ReplyOwnerValidator::class])
annotation class ReplyOwnerConstraint(
    val message: String = "본인이 쓴 글만 삭제 가능합니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
