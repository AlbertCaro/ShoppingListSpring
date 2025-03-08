package dev.albertocaro.shopping_list.infrastructure.auth.dto

import dev.albertocaro.shopping_list.domain.models.User

data class ProfileDto (
    val username: String,

    val email: String,

    val name: String,

    val lastName: String,
)

fun User.toProfile() = ProfileDto(username, email, name, lastName)