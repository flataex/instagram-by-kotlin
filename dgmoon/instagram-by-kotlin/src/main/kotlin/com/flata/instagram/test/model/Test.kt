package com.flata.instagram.test.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Test {
    @Id
    var id : Long? = null
    var name: String? = null
}