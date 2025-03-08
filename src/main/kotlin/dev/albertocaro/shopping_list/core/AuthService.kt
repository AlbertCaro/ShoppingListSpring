package dev.albertocaro.shopping_list.core

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.spec.SecretKeySpec

@Service
class AuthService {

    private val secretKeySpec = SecretKeySpec(SECRET_KEY.toByteArray(), "HmacSHA256")

    fun generateToken(id: Long): String {
        return Jwts.builder()
            .subject(id.toString())
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora de expiración
            .signWith(secretKeySpec)
            .compact()
    }

    fun validateToken(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKeySpec)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun getUserId(): Long? {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication != null && authentication.principal is Long) {
            return authentication.principal as Long
        }

        return null
    }

    companion object {
        const val SECRET_KEY = "e34a602468ef22a894a996d41f6a2bfcdf1e3f04182aeb2439897a4447534d93"
    }
}