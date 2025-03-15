package dev.albertocaro.shopping_list.domain.usecases.shoppingList.items

import dev.albertocaro.shopping_list.data.ShoppingListItemRepository

class DeleteItem(
    private val repository: ShoppingListItemRepository,
    private val getItemById: GetItemById
) {

    operator fun invoke(id: Long): Boolean? {
        val item = getItemById(id) ?: return null

        repository.delete(item)
        return true
    }
}