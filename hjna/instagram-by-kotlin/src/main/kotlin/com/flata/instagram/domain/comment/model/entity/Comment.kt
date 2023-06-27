package com.flata.instagram.domain.comment.model.entity

import com.flata.instagram.domain.feed.model.entity.Feed
import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "comment")
data class Comment(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    var feed: Feed,

    @Column(nullable = false)
    var content: String
) : BaseEntity()
