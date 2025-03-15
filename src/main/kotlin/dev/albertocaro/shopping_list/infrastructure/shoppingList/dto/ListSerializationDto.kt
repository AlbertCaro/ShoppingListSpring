package dev.albertocaro.shopping_list.infrastructure.shoppingList.dto

import dev.albertocaro.shopping_list.domain.models.ShoppingList
import java.util.Date

data class ListSerializationDto(
    val id: Long? = null,

    val name: String,

    val createdAt: Date,

    val items: List<ItemSerializationDto>,
)

fun ShoppingList.toSerialization() = ListSerializationDto(id, name, createdAt, items.map { it.toSerialization() })
