package dev.albertocaro.shopping_list.infrastructure.common

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleValidationException(exception: MethodArgumentNotValidException): Map<String, Any> {
        val errors = mutableMapOf<String, String>()

        exception.bindingResult.fieldErrors.forEach { error ->
            errors[error.field] = error.defaultMessage ?: "Error desconocido"
        }

        return mapOf("message" to "Errores de validación", "fields" to errors)
    }
}


/**
 *    {
 *      "message": "Errores de validación",
 *      "fields": {
 *          "username" : "El campo n..."
 *      }
 *    }
 */