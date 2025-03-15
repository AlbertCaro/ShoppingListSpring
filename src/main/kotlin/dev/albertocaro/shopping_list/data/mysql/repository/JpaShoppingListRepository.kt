package dev.albertocaro.shopping_list.data.mysql.repository

import dev.albertocaro.shopping_list.data.mysql.entity.ShoppingListEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaShoppingListRepository : JpaRepository<ShoppingListEntity, Long> {

    fun findByUserId(id: Long): List<ShoppingListEntity>
}