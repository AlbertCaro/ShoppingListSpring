package dev.albertocaro.shopping_list.domain.models

import dev.albertocaro.shopping_list.data.mysql.entity.ShoppingListItemEntity

data class ShoppingListItem(
    val id: Long? = null,

    var bought: Boolean = false,

    var quantity: Int,

    val product: Product,

    val list: ShoppingList? = null,
)

fun ShoppingListItemEntity.toDomain() = ShoppingListItem(id, bought, quantity, product.toDomain())
