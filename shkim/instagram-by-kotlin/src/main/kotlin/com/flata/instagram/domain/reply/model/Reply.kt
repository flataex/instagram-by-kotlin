package com.flata.instagram.domain.reply.model

import com.flata.instagram.domain.comment.model.Comment
import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "reply")
@Where(clause = "deleted_at is null")
class Reply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    var comment: Comment,

    @Column(name = "content", columnDefinition = "text")
    var content: String
): BaseEntity()