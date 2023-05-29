package com.flata.instagram.domain.file.model

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "file")
class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    var feed: Feed,

    @Column(name = "url", nullable = false, unique = true)
    var url: String
): BaseEntity()