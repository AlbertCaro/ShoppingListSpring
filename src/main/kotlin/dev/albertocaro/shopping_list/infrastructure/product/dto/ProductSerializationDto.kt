package dev.albertocaro.shopping_list.infrastructure.product.dto

import dev.albertocaro.shopping_list.domain.models.Product

data class ProductSerializationDto(
    val id: Long,

    val name: String,
)

fun Product.toSerialization() = ProductSerializationDto(id!!, name)
