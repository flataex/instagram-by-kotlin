package com.flata.instagram.domain.feed.model

import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "feed")
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "content", nullable = false, columnDefinition = "text")
    var content: String
): BaseEntity()