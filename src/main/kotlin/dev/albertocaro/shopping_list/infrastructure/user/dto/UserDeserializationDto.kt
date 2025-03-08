package dev.albertocaro.shopping_list.infrastructure.user.dto

import dev.albertocaro.shopping_list.infrastructure.user.validation.UniqueUserEmail
import dev.albertocaro.shopping_list.infrastructure.user.validation.UniqueUsername
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UserDeserializationDto(
    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    @field:UniqueUsername
    val username: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 8, max = 255)
    val password: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    @field:Email
    @field:UniqueUserEmail
    val email: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    val name: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    val lastName: String?,
)

