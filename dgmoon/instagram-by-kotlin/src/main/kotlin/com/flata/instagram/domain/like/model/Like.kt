package com.flata.instagram.domain.like.model

import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
@Table(name = "likes")
class Like(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "user_id", nullable = false)
    @Comment("사용자 ID")
    var userId: Long,
    @Column(name = "feed_id", nullable = false)
    @Comment("게시글 ID")
    var feedId: Long
)