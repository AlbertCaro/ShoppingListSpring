package dev.albertocaro.shopping_list.infrastructure.user.validation

import dev.albertocaro.shopping_list.domain.usecases.user.GetUserByUsernameUseCase
import dev.albertocaro.shopping_list.infrastructure.common.RequestProviderService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.http.HttpMethod

class UniqueUsernameValidator(
    private val getUserByUsernameUseCase: GetUserByUsernameUseCase,
    private val requestProviderService: RequestProviderService,
) : ConstraintValidator<UniqueUsername, String?> {

    private lateinit var errorMessage: String

    override fun initialize(constraintAnnotation: UniqueUsername) {
        errorMessage = constraintAnnotation.message
    }

    override fun isValid(username: String?, context: ConstraintValidatorContext): Boolean {
        if (username.isNullOrBlank()) return true

        val user = getUserByUsernameUseCase(username) ?: return true

        val request = requestProviderService.getRequest()

        if (request!!.method == HttpMethod.PUT.name()) {
            val editedUserId = request.requestURI.replace("/users/", "").toLong()

            if (user.id == editedUserId) return true
        }

        val finalErrorMessage = errorMessage.replace("{username}", username)

        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(finalErrorMessage)
            .addPropertyNode("username")
            .addConstraintViolation()

        return false
    }
}