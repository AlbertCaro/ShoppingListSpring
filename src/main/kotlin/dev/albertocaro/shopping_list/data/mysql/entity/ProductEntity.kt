package dev.albertocaro.shopping_list.data.mysql.entity

import dev.albertocaro.shopping_list.domain.models.Product
import jakarta.persistence.*

@Entity
@Table(name = "products")
data class ProductEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String
)

fun Product.toDatabase() = ProductEntity(id, name)