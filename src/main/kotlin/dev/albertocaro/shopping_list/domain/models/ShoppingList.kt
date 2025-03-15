package dev.albertocaro.shopping_list.domain.models

import dev.albertocaro.shopping_list.data.mysql.entity.ShoppingListEntity
import dev.albertocaro.shopping_list.infrastructure.shoppingList.dto.ListDeserializationDto
import java.util.*

data class ShoppingList (
    val id: Long? = null,

    var name: String,

    val createdAt: Date = Date(),

    val items: List<ShoppingListItem> = mutableListOf(),

    var user: User? = null,
)

fun ShoppingListEntity.toDomain() = ShoppingList(id, name, createdAt, items.map { it.toDomain() }, user!!.toDomain())

fun ListDeserializationDto.toDomain() = ShoppingList(null, name)