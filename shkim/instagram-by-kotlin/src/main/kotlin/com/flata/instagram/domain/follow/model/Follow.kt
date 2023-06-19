package com.flata.instagram.domain.follow.model

import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "follow")
class Follow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", nullable = false)
    var fromUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", nullable = false)
    var toUser: User
): BaseEntity()