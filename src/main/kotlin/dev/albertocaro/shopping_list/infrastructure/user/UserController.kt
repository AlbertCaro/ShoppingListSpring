package dev.albertocaro.shopping_list.infrastructure.user

import dev.albertocaro.shopping_list.domain.models.User
import dev.albertocaro.shopping_list.domain.models.toDomain
import dev.albertocaro.shopping_list.domain.usecases.user.*
import dev.albertocaro.shopping_list.infrastructure.user.dto.UserDeserializationDto
import dev.albertocaro.shopping_list.infrastructure.user.dto.UserSerializationDto
import dev.albertocaro.shopping_list.infrastructure.user.dto.toSerialization
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val editUserUseCase: EditUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) {

    @GetMapping
    fun fetchAll(): ResponseEntity<List<UserSerializationDto>> {
        val users = getAllUsersUseCase().map { it.toSerialization() }

        return ResponseEntity.ok(users)
    }

    @GetMapping("/{id}")
    fun fetchOne(@PathVariable id: Long): ResponseEntity<UserSerializationDto> {
        val user = getUserByIdUseCase(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(user.toSerialization())
    }

    @PostMapping
    fun create(@Valid @RequestBody user: UserDeserializationDto): ResponseEntity<UserSerializationDto> {
        val createdUser = saveUserUseCase(user.toDomain())

        return ResponseEntity.ok(createdUser.toSerialization())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody user: UserDeserializationDto): ResponseEntity<UserSerializationDto> {
        if (getUserByIdUseCase(id) == null) {
            return ResponseEntity.notFound().build()
        }

        val modifiedUser = editUserUseCase(id, user.toDomain())

        return ResponseEntity.ok(modifiedUser.toSerialization())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        val user = getUserByIdUseCase(id) ?: return ResponseEntity.notFound().build()

        deleteUserUseCase(user)

        return ResponseEntity.noContent().build()
    }
}