package dev.albertocaro.shopping_list.di

import dev.albertocaro.shopping_list.data.ShoppingListItemRepository
import dev.albertocaro.shopping_list.data.ShoppingListRepository
import dev.albertocaro.shopping_list.domain.usecases.auth.GetProfileUseCase
import dev.albertocaro.shopping_list.domain.usecases.shoppingList.*
import dev.albertocaro.shopping_list.domain.usecases.shoppingList.items.DeleteItem
import dev.albertocaro.shopping_list.domain.usecases.shoppingList.items.EditItem
import dev.albertocaro.shopping_list.domain.usecases.shoppingList.items.GetItemById
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ShoppingListConfig {

    @Bean
    fun createShoppingList(repository: ShoppingListRepository, getProfileUseCase: GetProfileUseCase) =
        CreateShoppingList(repository, getProfileUseCase)

    @Bean
    fun deleteShoppingList(repository: ShoppingListRepository, getShoppingListById: GetShoppingListById) =
        DeleteShoppingList(repository, getShoppingListById)

    @Bean
    fun editShoppingList(repository: ShoppingListRepository, getShoppingListById: GetShoppingListById) =
        EditShoppingList(repository, getShoppingListById)

    @Bean
    fun getAllShoppingLists(getProfileUseCase: GetProfileUseCase, repository: ShoppingListRepository) =
        GetAllUserShoppingLists(getProfileUseCase, repository)

    @Bean
    fun getShoppingListById(repository: ShoppingListRepository) = GetShoppingListById(repository)

    @Bean
    fun getShoppingListItems(getShoppingListById: GetShoppingListById) = GetShoppingListItems(getShoppingListById)

    @Bean
    fun deleteItem(repository: ShoppingListItemRepository, getItemById: GetItemById) =
        DeleteItem(repository, getItemById)

    @Bean
    fun editItem(repository: ShoppingListItemRepository, getItemById: GetItemById) =
        EditItem(repository, getItemById)

    @Bean
    fun getItemById(repository: ShoppingListItemRepository) = GetItemById(repository)
}