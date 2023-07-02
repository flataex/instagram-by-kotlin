package com.flata.instagram.domain.comment.model

import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "user_id", nullable = false)
    @Comment("사용자 ID")
    var userId: Long,
    @Column(name = "feed_id", nullable = false)
    @Comment("게시글 ID")
    var feedId: Long,
    @Column(columnDefinition = "text", nullable = false)
    @Comment("댓글")
    var content: String,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    var replies: List<Reply>?
) : BaseEntity() {
    fun update(content: String) {
        this.content = content
    }
}