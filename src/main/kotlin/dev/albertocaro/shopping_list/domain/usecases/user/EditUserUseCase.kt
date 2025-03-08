package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.core.PasswordEncoderService
import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.domain.models.User

class EditUserUseCase(
    private val repository: UserRepository,
    private val passwordEncoderService: PasswordEncoderService
) {

    operator fun invoke(iriId: Long, user: User): User {
        user.apply {
            id = iriId
            password = passwordEncoderService.encode(password)!!
        }

        return repository.saveUser(user)
    }
}