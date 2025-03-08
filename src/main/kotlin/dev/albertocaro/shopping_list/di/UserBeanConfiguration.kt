package dev.albertocaro.shopping_list.di

import dev.albertocaro.shopping_list.core.PasswordEncoderService
import dev.albertocaro.shopping_list.data.UserRepository
import dev.albertocaro.shopping_list.domain.usecases.user.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserBeanConfiguration {

    @Bean
    fun getAllUsers(repository: UserRepository) = GetAllUsersUseCase(repository)

    @Bean
    fun getUserByIdUseCase(repository: UserRepository) = GetUserByIdUseCase(repository)

    @Bean
    fun saveUserUseCase(repository: UserRepository, passwordEncoderService: PasswordEncoderService) = SaveUserUseCase(repository, passwordEncoderService)

    @Bean
    fun editUserUseCase(repository: UserRepository, passwordEncoderService: PasswordEncoderService) = EditUserUseCase(repository, passwordEncoderService)

    @Bean
    fun deleteUserUseCase(repository: UserRepository) = DeleteUserUseCase(repository)

    @Bean
    fun getUserByEmailUseCase(repository: UserRepository) = GetUserByEmailUseCase(repository)

    @Bean
    fun getUserByUsernameUseCase(repository: UserRepository) = GetUserByUsernameUseCase(repository)
}