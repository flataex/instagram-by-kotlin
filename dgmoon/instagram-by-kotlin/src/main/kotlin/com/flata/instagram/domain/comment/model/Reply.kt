package com.flata.instagram.domain.comment.model

import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
class Reply(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "user_id", nullable = false)
    @Comment("사용자 ID")
    var userId: Long,
    @Column(name = "comment_id", nullable = false)
    @Comment("댓글 ID")
    var commentId: Long,
    @Column(columnDefinition = "text", nullable = false)
    @Comment("대댓글")
    var content: String
) : BaseEntity() {
    fun update(content: String) {
        this.content = content
    }
}