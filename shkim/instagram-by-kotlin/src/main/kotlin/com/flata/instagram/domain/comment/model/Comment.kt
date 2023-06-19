package com.flata.instagram.domain.comment.model

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "comment")
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    var feed: Feed,

    @Column(name = "content", columnDefinition = "text")
    var content: String
): BaseEntity()