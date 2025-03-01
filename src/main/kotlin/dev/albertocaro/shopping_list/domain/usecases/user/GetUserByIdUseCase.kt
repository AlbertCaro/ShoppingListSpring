package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.domain.models.User

class GetUserByIdUseCase (
    private val repository: UserRepository
)
{
    operator fun invoke(id: Long): User? {
        return repository.findById(id)
    }
}