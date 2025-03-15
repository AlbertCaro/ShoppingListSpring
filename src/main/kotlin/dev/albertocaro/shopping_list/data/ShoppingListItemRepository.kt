package dev.albertocaro.shopping_list.data

import dev.albertocaro.shopping_list.data.mysql.entity.toDatabase
import dev.albertocaro.shopping_list.data.mysql.repository.JpaShoppingListItemRepository
import dev.albertocaro.shopping_list.domain.models.ShoppingListItem
import dev.albertocaro.shopping_list.domain.models.toDomain
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class ShoppingListItemRepository(
    private val jpaRepository: JpaShoppingListItemRepository
) {

    fun findById(id: Long): ShoppingListItem? {
        val item = jpaRepository.findById(id).getOrNull() ?: return null

        return item.toDomain()
    }

    fun save(item: ShoppingListItem): ShoppingListItem {
        return jpaRepository.save(item.toDatabase()).toDomain()
    }

    fun delete(item: ShoppingListItem) {
        jpaRepository.delete(item.toDatabase())
    }
}