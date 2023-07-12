package com.flata.instagram.domain.follow.model

import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
class Follow(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @OneToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    @Comment("팔로우 하는 사람")
    var fromUser: User,
    @OneToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    @Comment("팔로우 당한 사람")
    var toUser: User
) : BaseEntity()