package com.flata.instagram.domain.file.model

import com.flata.instagram.domain.feed.model.Feed
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "file")
@SQLDelete(sql = "update file set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
class File(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id", nullable = false)
    @Comment("게시글 ID")
    var feed: Feed,

    @Column(name = "url", nullable = false, length = 256, unique = true)
    @Comment("파일 url")
    var url: String
): BaseEntity()
