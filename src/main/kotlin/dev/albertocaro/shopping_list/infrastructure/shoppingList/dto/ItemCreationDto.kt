package dev.albertocaro.shopping_list.infrastructure.shoppingList.dto

import org.jetbrains.annotations.NotNull

data class ItemCreationDto(
    @field:NotNull
    val product: Long? = null,

    @field:NotNull
    val quantity: Int? = null,

    @field:NotNull
    val bought: Boolean? = null,
)
