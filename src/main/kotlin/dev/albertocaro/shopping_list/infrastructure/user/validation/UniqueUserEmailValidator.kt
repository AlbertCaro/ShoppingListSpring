package dev.albertocaro.shopping_list.infrastructure.user.validation

import dev.albertocaro.shopping_list.domain.usecases.user.GetUserByEmailUseCase
import dev.albertocaro.shopping_list.infrastructure.common.RequestProviderService
import dev.albertocaro.shopping_list.infrastructure.user.dto.UserDeserializationDto
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class UniqueUserEmailValidator(
    private val getUserByEmail: GetUserByEmailUseCase,
    private val requestProviderService: RequestProviderService
) : ConstraintValidator<UniqueUserEmail, UserDeserializationDto> {

    private lateinit var message: String

    override fun initialize(constraintAnnotation: UniqueUserEmail) {
        message = constraintAnnotation.message
    }

    override fun isValid(value: UserDeserializationDto?, context: ConstraintValidatorContext): Boolean {
        if (value == null) return true

        if (value.email.isNullOrBlank() || value.email.isEmpty()) return true

        val user = getUserByEmail(value.email) ?: return true

        val request = requestProviderService.getRequest()

        if (request!!.method == "PUT") {
            val editUserId = request.requestURI!!.replace("/users/", "").toLong()

            if (user.id == editUserId) return true
        }

        val message = this.message.replace("{email}", value.email)
        context.disableDefaultConstraintViolation()
        context.buildConstraintViolationWithTemplate(message)
            .addPropertyNode("email")
            .addConstraintViolation()

        return false
    }
}