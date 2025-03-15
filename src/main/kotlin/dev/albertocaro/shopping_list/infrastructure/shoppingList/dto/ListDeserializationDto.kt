package dev.albertocaro.shopping_list.infrastructure.shoppingList.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ListDeserializationDto(
    @field:NotNull
    @field:NotBlank
    val name: String
)
