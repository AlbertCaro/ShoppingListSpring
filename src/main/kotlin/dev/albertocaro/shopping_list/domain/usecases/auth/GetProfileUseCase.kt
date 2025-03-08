package dev.albertocaro.shopping_list.domain.usecases.auth

import dev.albertocaro.shopping_list.core.AuthService
import dev.albertocaro.shopping_list.domain.models.User
import dev.albertocaro.shopping_list.domain.usecases.user.GetUserByIdUseCase

class GetProfileUseCase(
    private val authService: AuthService,
    private val getUserByIdUseCase: GetUserByIdUseCase
) {

    operator fun invoke(): User? {
        val userId = authService.getUserId()

        return userId?.let { getUserByIdUseCase(it) }
    }
}