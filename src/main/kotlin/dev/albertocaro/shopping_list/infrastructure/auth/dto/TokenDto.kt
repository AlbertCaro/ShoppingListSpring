package dev.albertocaro.shopping_list.infrastructure.auth.dto

data class TokenDto(
    val token: String,
    val message: String = "Autenticación correcta.",
)