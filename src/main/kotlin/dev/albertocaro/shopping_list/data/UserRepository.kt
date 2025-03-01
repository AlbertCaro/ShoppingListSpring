package dev.albertocaro.shopping_list.data

import dev.albertocaro.shopping_list.data.mysql.entity.toDatabase
import dev.albertocaro.shopping_list.data.mysql.repository.JpaUserRepository
import dev.albertocaro.shopping_list.domain.models.User
import dev.albertocaro.shopping_list.domain.models.toDomain
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserRepository(private val jpaRepository: JpaUserRepository) {

    fun findAll(): List<User> = jpaRepository.findAll().map { it.toDomain() }

    fun findByEmail(email: String): User? {
        return jpaRepository.findByEmail(email)?.toDomain()
    }

    fun findById(id: Long): User? {
        val entity = jpaRepository.findById(id)

        return entity.getOrNull()?.toDomain()
    }

    fun saveUser(user: User): User {
        return jpaRepository.save(user.toDatabase()).toDomain()
    }

    fun delete(user: User) {
        jpaRepository.delete(user.toDatabase())
    }
}