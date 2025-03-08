package dev.albertocaro.shopping_list.data.mysql.repository

import dev.albertocaro.shopping_list.data.mysql.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?

    fun findByUsername(username: String): UserEntity?
}