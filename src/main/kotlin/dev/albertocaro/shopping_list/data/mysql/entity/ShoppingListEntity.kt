package dev.albertocaro.shopping_list.data.mysql.entity

import dev.albertocaro.shopping_list.domain.models.ShoppingList
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "shopping_lists")
data class ShoppingListEntity(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, updatable = false)
    val createdAt: Date = Date(),

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: UserEntity? = null,

    @OneToMany(mappedBy = "list", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: List<ShoppingListItemEntity> = mutableListOf()
)

fun ShoppingList.toDatabase() = ShoppingListEntity(id, name, createdAt, user!!.toDatabase(), items.map { it.toDatabase() })
