package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.domain.models.User

class GetUserByUsernameUseCase (
    private val repository: UserRepository
) {

    operator fun invoke(username: String): User? {
        return repository.findByUsername(username)
    }
}