package com.flata.instagram.domain.user.model

import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    @Column(length = 32, nullable = false, unique = true)
    var email: String,
    @Column(length = 256, nullable = false)
    var password: String,
    @Column(length = 16, nullable = false, unique = true)
    var nickname: String
) : BaseEntity() {
    fun update(email: String, password: String, nickname: String) {
        this.email = email
        this.password = password
        this.nickname = nickname
    }
}