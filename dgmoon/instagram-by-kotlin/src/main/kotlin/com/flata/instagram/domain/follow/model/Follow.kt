package com.flata.instagram.domain.follow.model

import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
class Follow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "from_user_id", nullable = false)
    @Comment("팔로우 하는 사람")
    var fromUserId: Long,
    @Column(name = "to_user_id", nullable = false)
    @Comment("팔로우 당한 사람")
    var toUserId: Long
)