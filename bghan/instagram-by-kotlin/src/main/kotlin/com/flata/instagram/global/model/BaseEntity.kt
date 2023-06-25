package com.flata.instagram.global.model

import org.hibernate.annotations.Comment
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    @Comment("생성한 시간")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(name = "modified_at", nullable = false)
    @Comment("수정한 시간")
    var modifiedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "deleted_at")
    @Comment("삭제한 시간")
    var deletedAt: LocalDateTime? = null
) {
    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }
}