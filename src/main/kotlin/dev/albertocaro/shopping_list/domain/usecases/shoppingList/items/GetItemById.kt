package dev.albertocaro.shopping_list.domain.usecases.shoppingList.items

import dev.albertocaro.shopping_list.data.ShoppingListItemRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingListItem

class GetItemById(
    private val repository: ShoppingListItemRepository
) {

    operator fun invoke(id: Long): ShoppingListItem? {
        return repository.findById(id)
    }
}