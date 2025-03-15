package dev.albertocaro.shopping_list.domain.usecases.shoppingList

import dev.albertocaro.shopping_list.data.ShoppingListRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingList
import dev.albertocaro.shopping_list.domain.usecases.auth.GetProfileUseCase

class CreateShoppingList(
    private val repository: ShoppingListRepository,
    private val getProfileUseCase: GetProfileUseCase
) {

    operator fun invoke(shoppingList: ShoppingList): ShoppingList {
        val currentUser = getProfileUseCase()

        shoppingList.user = currentUser

        return repository.save(shoppingList)
    }
}