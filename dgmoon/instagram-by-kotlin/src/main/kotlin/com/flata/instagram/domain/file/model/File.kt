package com.flata.instagram.domain.file.model

import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(nullable = false, unique = true)
    var url: String,
    @Column(name = "feed_id", nullable = false)
    var feedId: Long
) : BaseEntity() {
    fun update(url: String, feedId: Long) {
        this.url = url
        this.feedId = feedId
    }
}
