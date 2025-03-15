package dev.albertocaro.shopping_list.domain.models

import dev.albertocaro.shopping_list.data.mysql.entity.ProductEntity

data class Product(
    val id: Long? = null,

    val name: String
)

fun ProductEntity.toDomain() = Product(id, name)