package dev.albertocaro.shopping_list.infrastructure.user.dto

import dev.albertocaro.shopping_list.domain.models.User

data class UserSerializationDto(
    val id: Long,

    val username: String,

    val email: String,

    val name: String,

    val lastName: String,
)

fun User.toSerialization() = UserSerializationDto(id!!, username, email, name, lastName)