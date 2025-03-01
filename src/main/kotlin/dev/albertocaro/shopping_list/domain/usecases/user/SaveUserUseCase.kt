package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.data.mysql.entity.UserEntity
import dev.albertocaro.shopping_list.domain.models.User

class SaveUserUseCase(
    private val repository: UserRepository,
) {

    operator fun invoke(user: User): User {
        return repository.saveUser(user)
    }
}