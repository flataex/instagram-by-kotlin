package com.flata.instagram.domain.comment.model

import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "reply")
@SQLDelete(sql = "update reply set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
class Reply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @org.hibernate.annotations.Comment("사용자 ID")
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    @org.hibernate.annotations.Comment("댓글 ID")
    var comment: Comment,

    @Column(name = "content", nullable = false, columnDefinition = "text")
    var content: String
): BaseEntity()