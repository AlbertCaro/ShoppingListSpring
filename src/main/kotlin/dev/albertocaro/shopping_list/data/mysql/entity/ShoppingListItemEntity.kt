package dev.albertocaro.shopping_list.data.mysql.entity

import dev.albertocaro.shopping_list.domain.models.ShoppingListItem
import jakarta.persistence.*

@Entity
@Table(name = "shopping_list_items")
data class ShoppingListItemEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val bought: Boolean = false,

    @Column(nullable = false)
    val quantity: Int,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: ProductEntity,

    @ManyToOne
    @JoinColumn(name = "shopping_list_id", nullable = false)
    val list: ShoppingListEntity? = null,
)

fun ShoppingListItem.toDatabase() = ShoppingListItemEntity(id, bought, quantity, product.toDatabase())