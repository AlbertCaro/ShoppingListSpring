package dev.albertocaro.shopping_list.infrastructure.user.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [UniqueUserEmailValidator::class])
annotation class UniqueUserEmail(
    val message: String = "El correo electrónico \"{email}\" ya se encuentra utilizado",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
