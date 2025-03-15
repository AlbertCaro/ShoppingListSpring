package dev.albertocaro.shopping_list.domain.usecases.shoppingList

import dev.albertocaro.shopping_list.data.ShoppingListRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingList

class EditShoppingList(
    private val repository: ShoppingListRepository,
    private val getShoppingListById: GetShoppingListById,
) {

    operator fun invoke(id: Long, newShoppingList: ShoppingList): ShoppingList? {
        val list = getShoppingListById(id) ?: return null

        list.apply {
            name = newShoppingList.name
        }

        return repository.save(list)
    }
}