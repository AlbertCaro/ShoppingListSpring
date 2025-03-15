package dev.albertocaro.shopping_list.domain.usecases.shoppingList

import dev.albertocaro.shopping_list.data.ShoppingListRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingList

class GetShoppingListById(
    private val repository: ShoppingListRepository
) {

    operator fun invoke(id: Long): ShoppingList? {
        return repository.findById(id)
    }
}