package com.flata.instagram.domain.feed.validation.annotation

import com.flata.instagram.domain.feed.validation.validator.FeedOwnerValidator
import com.flata.instagram.domain.user.validation.validator.UniqueNicknameValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [FeedOwnerValidator::class])
annotation class FeedOwnerConstraint(
    val message: String = "해당 게시글을 작성한 사용자가 아니라 삭제할 수 없습니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
