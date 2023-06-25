package com.flata.instagram.domain.user.model

import com.flata.instagram.domain.follow.model.Follow
import com.flata.instagram.global.model.BaseEntity
import org.hibernate.annotations.Comment
import org.springframework.security.crypto.bcrypt.BCrypt
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(length = 32, nullable = false, unique = true)
    @Comment("이메일")
    var email: String,
    @Column(length = 256, nullable = false)
    @Comment("비밀번호")
    var password: String,
    @Column(length = 16, nullable = false, unique = true)
    @Comment("닉네임")
    var nickname: String,
    @OneToMany
    @JoinColumn(name = "follow")
    var follows: List<Follow>
) : BaseEntity() {
    fun update(email: String, password: String, nickname: String) {
        this.email = email
        this.password = BCrypt.hashpw(password, BCrypt.gensalt())
        this.nickname = nickname
    }
}