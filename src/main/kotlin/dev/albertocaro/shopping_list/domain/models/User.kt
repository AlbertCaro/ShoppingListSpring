package dev.albertocaro.shopping_list.domain.models

import dev.albertocaro.shopping_list.data.mysql.entity.UserEntity
import dev.albertocaro.shopping_list.infrastructure.user.dto.UserDeserializationDto

data class User(
    var id: Long? = null,

    val username: String,

    val password: String,

    val email: String,

    val name: String,

    val lastName: String,
)

fun UserEntity.toDomain() = User(id, username, password, email, name, lastName)

fun UserDeserializationDto.toDomain() = User(null, username!!, password!!, email!!, name!!, lastName!!)