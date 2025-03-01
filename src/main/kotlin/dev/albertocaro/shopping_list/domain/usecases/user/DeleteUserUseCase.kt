package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.domain.models.User

class DeleteUserUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(user: User) {
        repository.delete(user)
    }
}