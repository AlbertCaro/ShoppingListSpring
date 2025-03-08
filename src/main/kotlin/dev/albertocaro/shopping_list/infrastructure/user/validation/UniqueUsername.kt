package dev.albertocaro.shopping_list.infrastructure.user.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Constraint(validatedBy = [UniqueUsernameValidator::class])
annotation class UniqueUsername(
    val message: String = "El nombre de usuario \"{username}\" ya se encuentra utilizado",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
)
