package dev.albertocaro.shopping_list.infrastructure.shoppingList.dto

import dev.albertocaro.shopping_list.domain.models.ShoppingListItem
import dev.albertocaro.shopping_list.infrastructure.product.dto.ProductSerializationDto
import dev.albertocaro.shopping_list.infrastructure.product.dto.toSerialization

data class ItemSerializationDto(
    val id: Long? = null,

    var bought: Boolean = false,

    var quantity: Int,

    val product: ProductSerializationDto,
)

fun ShoppingListItem.toSerialization() = ItemSerializationDto(id, bought, quantity, product.toSerialization())