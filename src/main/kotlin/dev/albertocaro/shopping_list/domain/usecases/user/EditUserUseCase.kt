package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.domain.models.User

class EditUserUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(id: Long, user: User): User {
        user.id = id

        return repository.saveUser(user)
    }
}