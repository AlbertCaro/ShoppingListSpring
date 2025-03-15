package dev.albertocaro.shopping_list.domain.usecases.shoppingList.items

import dev.albertocaro.shopping_list.data.ShoppingListItemRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingListItem

class EditItem(
    private val repository: ShoppingListItemRepository,
    private val getItemById: GetItemById
) {

    operator fun invoke(id: Long, newItem: ShoppingListItem): ShoppingListItem? {
        val item = getItemById(id) ?: return null

        item.apply {
            bought = newItem.bought
            quantity = newItem.quantity
        }

        return repository.save(item)
    }
}