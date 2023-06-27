package com.flata.instagram.domain.file.model.entity

import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "file")
data class File(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(length = 256, nullable = false, unique = true)
        val url: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "feed_id", nullable = false)
        val feed: Feed
): BaseEntity()