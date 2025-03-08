package dev.albertocaro.shopping_list.core

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordEncoderService(
    private val passwordEncoder: PasswordEncoder
) {

    fun encode(value: String): String? {
        return passwordEncoder.encode(value) // BCrypt
    }

    fun matches(value: String, encryptedValue: String): Boolean {
        return passwordEncoder.matches(encryptedValue, value)
    }
}