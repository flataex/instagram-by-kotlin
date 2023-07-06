package com.flata.instagram.global.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {

    @Value("\${jwt.secret}")
    private val jwtSecret: String = ""

    @Value("\${jwt.expiration}")
    private val jwtExpiration: Long = 0

    fun generateJwtToken(userId: Long): String {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(Date())
            .setExpiration(Date(Date().time + jwtExpiration))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }


    fun getIdFromJwtToken(jwt: String): Long {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).body.subject.toLong()
    }
}