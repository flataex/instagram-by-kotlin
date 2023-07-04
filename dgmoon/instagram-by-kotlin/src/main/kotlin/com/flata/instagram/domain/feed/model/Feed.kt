package com.flata.instagram.domain.feed.model

import com.flata.instagram.domain.file.model.File
import com.flata.instagram.domain.like.model.Like
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
    var content: String,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="feed_id")
    var comments: List<com.flata.instagram.domain.comment.model.Comment>?,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id")
    var files: List<File>?,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="like_id")
    var likes: List<Like>?
) : BaseEntity() {
    fun update(userId: Long, text: String) {
        this.userId = userId
        this.content = text
    }
}
