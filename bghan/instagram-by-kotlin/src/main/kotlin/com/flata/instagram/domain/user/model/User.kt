package com.flata.instagram.domain.user.model

import com.flata.instagram.domain.user.converter.PasswordEncryptConverter
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Table(name = "users")
@SQLDelete(sql = "update user set deleted_at = now() where id = ?")
@Where(clause = "deleted_at is null")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "email", nullable = false, length = 32, unique = true)
    @Comment("사용자 이메일")
    var email: String,

    @Convert(converter = PasswordEncryptConverter::class)
    @Column(name = "password", nullable = false, length = 256)
    @Comment("비밀번호")
    var password: String,

    @Column(name = "nickname", nullable = false, length = 16, unique = true)
    @Comment("닉네임")
    var nickname: String
): BaseEntity() {
    fun isSame(userId: Long): Boolean = this.id.compareTo(userId) == 0
}