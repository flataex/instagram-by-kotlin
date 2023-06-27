package com.flata.instagram.domain.like.model

import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "likes")
data class Like(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    var feed: Feed
) : BaseEntity()