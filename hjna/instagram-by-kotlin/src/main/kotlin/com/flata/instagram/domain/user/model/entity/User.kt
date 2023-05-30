package com.flata.instagram.domain.user.model.entity

import com.flata.instagram.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(length = 32, nullable = false, unique = true)
        val email: String,

        @Column(length = 256, nullable = false)
        val password: String,

        @Column(length = 16, nullable = false, unique = true)
        val nickname: String
): BaseEntity()