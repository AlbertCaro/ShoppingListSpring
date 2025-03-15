package dev.albertocaro.shopping_list.data.mysql.repository

import dev.albertocaro.shopping_list.data.mysql.entity.ShoppingListItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaShoppingListItemRepository : JpaRepository<ShoppingListItemEntity, Long> {
}