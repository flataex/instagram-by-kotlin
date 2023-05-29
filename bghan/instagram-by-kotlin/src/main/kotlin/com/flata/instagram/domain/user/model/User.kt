package com.flata.instagram.domain.user.model

import com.flata.instagram.global.model.BaseEntity
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "email", nullable = false, length = 32, unique = true)
    var email: String,

    @Column(name = "password", nullable = false, length = 256)
    var password: String,

    @Column(name = "nickname", nullable = false, length = 16, unique = true)
    var nickname: String
): BaseEntity()