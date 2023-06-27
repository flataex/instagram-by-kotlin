package com.flata.instagram.domain.reply.model

import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "reply")
data class Reply(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    var comment: Comment,

    @Column(nullable = false)
    var content: String
) : BaseEntity()
