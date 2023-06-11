package com.flata.instagram.domain.feed.model

import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
class Feed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(name = "user_id", nullable = false)
    var userId: Long,
    @Column(nullable = false)
    var text: String
) : BaseEntity() {
    fun update(userId: Long, text: String) {
        this.userId = userId
        this.text = text
    }
}
