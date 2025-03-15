package dev.albertocaro.shopping_list.data.mysql.entity

import dev.albertocaro.shopping_list.domain.models.User
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class UserEntity (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val lastName: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    val shoppingLists: List<ShoppingListEntity> = mutableListOf()
)

fun User.toDatabase() = UserEntity(id, username, password, email, name, lastName)