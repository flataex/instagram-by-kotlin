package com.flata.instagram.domain.follow.model

import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "follow")
@SQLDelete(sql = "update follow set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
class Follow (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", nullable = false)
    @Comment("팔로우 하는 사용자 ID")
    var fromUser: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", nullable = false)
    @Comment("팔로우 당한 사용자 ID")
    var toUser: User
): BaseEntity()