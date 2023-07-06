package com.flata.instagram.domain.feed.model.entity

import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "feed")
data class Feed(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user: User,

        @Column(nullable = false)
        var content: String
): BaseEntity()