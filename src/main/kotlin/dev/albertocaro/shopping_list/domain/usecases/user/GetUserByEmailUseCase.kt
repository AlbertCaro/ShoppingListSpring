package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.domain.models.User

class GetUserByEmailUseCase(
    private val repository: UserRepository
) {

    operator fun invoke(email: String): User? {
        return repository.findByEmail(email)
    }
}