package dev.albertocaro.shopping_list.domain.usecases.auth

import dev.albertocaro.shopping_list.core.AuthService
import dev.albertocaro.shopping_list.core.PasswordEncoderService
import dev.albertocaro.shopping_list.domain.usecases.user.GetUserByUsernameUseCase

class GetTokenOnLoginUseCase(
    private val authService: AuthService,
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase,
    private val passwordEncoderService: PasswordEncoderService
) {

    operator fun invoke(username: String, password: String): String? {
        val user = getUserByUsernameUseCase(username) ?: return null

        if (!passwordEncoderService.matches(user.password, password)) {
            return null
        }

        return authService.generateToken(user.id!!)
    }
}