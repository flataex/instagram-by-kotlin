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
    @Column(name = "from_user_id", nullable = false)
    @Comment("팔로우 하는 사람")
    @OneToOne
    @JoinColumn(name = "user_id")
    var fromUser: User,
    @Column(name = "to_user_id", nullable = false)
    @Comment("팔로우 당한 사람")
    @OneToOne
    @JoinColumn(name = "user_id")
    var toUser: User
) : BaseEntity()