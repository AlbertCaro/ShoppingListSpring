package dev.albertocaro.shopping_list.di

import dev.albertocaro.shopping_list.core.AuthService
import dev.albertocaro.shopping_list.core.PasswordEncoderService
import dev.albertocaro.shopping_list.domain.usecases.auth.GetProfileUseCase
import dev.albertocaro.shopping_list.domain.usecases.auth.GetTokenOnLoginUseCase
import dev.albertocaro.shopping_list.domain.usecases.user.GetUserByIdUseCase
import dev.albertocaro.shopping_list.domain.usecases.user.GetUserByUsernameUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AuthConfig {
    @Bean
    fun getTokenOnLogin(
        authService: AuthService,
        getUserByUsernameUseCase: GetUserByUsernameUseCase,
        passwordEncoderService: PasswordEncoderService,
    ) = GetTokenOnLoginUseCase(authService, getUserByUsernameUseCase, passwordEncoderService)

    @Bean
    fun getProfile(authService: AuthService, getUserByIdUseCase: GetUserByIdUseCase) =
        GetProfileUseCase(authService, getUserByIdUseCase)
}