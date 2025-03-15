package dev.albertocaro.shopping_list.domain.usecases.shoppingList

import dev.albertocaro.shopping_list.data.ShoppingListRepository

class DeleteShoppingList(
    private val repository: ShoppingListRepository,
    private val getShoppingListById: GetShoppingListById,
) {

    operator fun invoke(id: Long): Boolean? {
        val list = getShoppingListById(id) ?: return null

        repository.delete(list)

        return true
    }
}