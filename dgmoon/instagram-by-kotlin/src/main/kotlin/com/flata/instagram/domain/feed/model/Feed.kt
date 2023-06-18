package com.flata.instagram.domain.feed.model

import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "user_id", nullable = false)
    @Comment("사용자 ID")
    var userId: Long,
    @Column(columnDefinition = "text", nullable = false)
    @Comment("게시글")
    var content: String
) : BaseEntity() {
    fun update(userId: Long, text: String) {
        this.userId = userId
        this.content = text
    }
}