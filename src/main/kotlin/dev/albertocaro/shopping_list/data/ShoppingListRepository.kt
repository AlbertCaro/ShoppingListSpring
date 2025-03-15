package dev.albertocaro.shopping_list.data

import dev.albertocaro.shopping_list.data.mysql.entity.toDatabase
import dev.albertocaro.shopping_list.data.mysql.repository.JpaShoppingListRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingList
import dev.albertocaro.shopping_list.domain.models.toDomain
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class ShoppingListRepository(
    private val jpaRepository: JpaShoppingListRepository
) {

    fun findByUserId(userId: Long): List<ShoppingList> {
        return jpaRepository.findByUserId(userId).map { it.toDomain() }
    }

    fun findById(id: Long): ShoppingList? {
        val list = jpaRepository.findById(id).getOrNull() ?: return null

        return list.toDomain()
    }

    fun save(shoppingList: ShoppingList): ShoppingList {
        return jpaRepository.save(shoppingList.toDatabase()).toDomain()
    }

    fun delete(shoppingList: ShoppingList) {
        jpaRepository.delete(shoppingList.toDatabase())
    }
}