package com.flata.instagram.domain.feed.model

import com.flata.instagram.domain.user.model.User
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "feed")
@SQLDelete(sql = "update feed set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @Comment("사용자 ID")
    var user: User,

    @Column(name = "content", nullable = false, columnDefinition = "text")
    @Comment("게시글")
    var content: String
): BaseEntity() {
    fun isOwner(userId: Long): Boolean = this.user.isSame(userId)
}