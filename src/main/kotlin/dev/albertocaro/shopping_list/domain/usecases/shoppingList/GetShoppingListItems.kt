package dev.albertocaro.shopping_list.domain.usecases.shoppingList

import dev.albertocaro.shopping_list.domain.models.ShoppingListItem

class GetShoppingListItems(
    private val getShoppingListById: GetShoppingListById
) {

    operator fun invoke(id: Long): List<ShoppingListItem>? {
        val list = getShoppingListById(id) ?: return null

        return list.items
    }
}