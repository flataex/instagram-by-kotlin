package com.flata.instagram.domain.follow.model

import com.flata.instagram.domain.user.model.entity.User
import com.flata.instagram.global.entity.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "follow")
data class Follow(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", nullable = false)
    var fromUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", nullable = false)
    var toUser: User
) : BaseEntity()
