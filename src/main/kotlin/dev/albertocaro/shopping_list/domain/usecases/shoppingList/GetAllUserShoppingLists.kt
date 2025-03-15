package dev.albertocaro.shopping_list.domain.usecases.shoppingList

import dev.albertocaro.shopping_list.data.ShoppingListRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingList
import dev.albertocaro.shopping_list.domain.usecases.auth.GetProfileUseCase

class GetAllUserShoppingLists(
    private val getProfileUseCase: GetProfileUseCase,
    private val repository: ShoppingListRepository
) {

    operator fun invoke(): List<ShoppingList> {
        val user = getProfileUseCase()

        return repository.findByUserId(user!!.id!!)
    }
}