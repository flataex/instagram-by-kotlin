package com.flata.instagram.domain.like.model

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "likes")
@SQLDelete(sql = "update likes set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
class Like(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("사용자 ID")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    @Comment("게시글 ID")
    var feed: Feed
): BaseEntity()