package dev.albertocaro.shopping_list.infrastructure.auth

import dev.albertocaro.shopping_list.di.OpenApiConfig
import dev.albertocaro.shopping_list.domain.usecases.auth.GetProfileUseCase
import dev.albertocaro.shopping_list.domain.usecases.auth.GetTokenOnLoginUseCase
import dev.albertocaro.shopping_list.infrastructure.auth.dto.LoginDto
import dev.albertocaro.shopping_list.infrastructure.auth.dto.ProfileDto
import dev.albertocaro.shopping_list.infrastructure.auth.dto.TokenDto
import dev.albertocaro.shopping_list.infrastructure.auth.dto.toProfile
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.tags.Tag
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Módulo de autenticación")
class AuthController(
    private val getTokenOnLoginUseCase: GetTokenOnLoginUseCase,
    private val getProfileUseCase: GetProfileUseCase
) {

    @PostMapping("/login")
    @Operation(
        summary = "Login del sistema",
        description = "Recibe el usuario y contraseña, si las credenciales son válidas proporciona un token JWT.",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Token generado correctamente",
                content = [Content(schema = Schema(implementation = TokenDto::class))]
            )
        ]
    )
    fun login(@Valid @RequestBody loginDto: LoginDto): ResponseEntity<out Any> {
        val token = getTokenOnLoginUseCase(loginDto.username!!, loginDto.password!!)

        if (token != null) {
            return ResponseEntity.ok(TokenDto(token))
        }

        return ResponseEntity.status(401).body(mapOf("error" to "Credenciales inválidas"))
    }

    @GetMapping("/profile")
    @Operation(
        summary = "Perfil del usuario autenticado",
        description = "Obtiene la información del usuario logeado."
    )
    @SecurityRequirement(name = OpenApiConfig.SECURITY_SCHEME_NAME)
    fun profile(): ResponseEntity<ProfileDto> {
        val user = getProfileUseCase()

        if (user === null) {
            ResponseEntity.internalServerError().build<ProfileDto>()
        }

        return ResponseEntity.ok().body(user!!.toProfile())
    }
}