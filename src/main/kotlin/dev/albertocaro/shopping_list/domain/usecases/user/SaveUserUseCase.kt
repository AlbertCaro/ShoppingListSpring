package dev.albertocaro.shopping_list.domain.usecases.user

import dev.albertocaro.shopping_list.core.PasswordEncoderService
import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.data.mysql.entity.UserEntity
import dev.albertocaro.shopping_list.domain.models.User

class SaveUserUseCase(
    private val repository: UserRepository,
    private val passwordEncoderService: PasswordEncoderService,
) {

    operator fun invoke(user: User): User {
        user.apply {
            password = passwordEncoderService.encode(password)!!
        }

        return repository.saveUser(user)
    }
}