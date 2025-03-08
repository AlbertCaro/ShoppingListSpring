package dev.albertocaro.shopping_list.infrastructure.user.validation

import dev.albertocaro.shopping_list.domain.usecases.user.GetUserByEmailUseCase
import dev.albertocaro.shopping_list.infrastructure.common.RequestProviderService
import dev.albertocaro.shopping_list.infrastructure.user.dto.UserDeserializationDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.http.HttpMethod

class UniqueUserEmailValidator(
    private val getUserByEmail: GetUserByEmailUseCase,
    private val requestProviderService: RequestProviderService
) : ConstraintValidator<UniqueUserEmail, String> {

    private lateinit var message: String

    override fun initialize(constraintAnnotation: UniqueUserEmail) {
        message = constraintAnnotation.message
    }

    override fun isValid(email: String?, context: ConstraintValidatorContext): Boolean {
        if (email == null) return true

        if (email.isBlank() || email.isEmpty()) return true

        val user = getUserByEmail(email) ?: return true

        val request = requestProviderService.getRequest()

        if (request!!.method == HttpMethod.PUT.name()) {
            val editUserId = request.requestURI!!.replace("/users/", "").toLong()

            if (user.id == editUserId) return true
        }

        val message = this.message.replace("{email}", email)
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(message)
            .addPropertyNode("email")
            .addConstraintViolation()

        return false
    }
}