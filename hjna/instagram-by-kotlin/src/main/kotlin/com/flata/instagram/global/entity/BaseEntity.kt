package com.flata.instagram.global.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@MappedSuperclass
open class BaseEntity(
        @CreationTimestamp
        @Column(name = "created_at", nullable = false)
        var createdAt: LocalDateTime = LocalDateTime.now(),

        @UpdateTimestamp
        @Column(name = "modified_at", nullable = false)
        var modifiedAt: LocalDateTime = LocalDateTime.now(),

        @Column(name = "deleted_at")
        var deletedAt: LocalDateTime? = null
)
