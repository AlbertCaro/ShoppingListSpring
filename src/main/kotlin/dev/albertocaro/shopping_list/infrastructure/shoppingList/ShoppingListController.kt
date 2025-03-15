package dev.albertocaro.shopping_list.infrastructure.shoppingList

import dev.albertocaro.shopping_list.domain.models.toDomain
import dev.albertocaro.shopping_list.domain.usecases.shoppingList.*
import dev.albertocaro.shopping_list.infrastructure.shoppingList.dto.ItemSerializationDto
import dev.albertocaro.shopping_list.infrastructure.shoppingList.dto.ListDeserializationDto
import dev.albertocaro.shopping_list.infrastructure.shoppingList.dto.ListSerializationDto
import dev.albertocaro.shopping_list.infrastructure.shoppingList.dto.toSerialization
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
@RequestMapping("/shopping_lists")
class ShoppingListController(
    private val getShoppingListById: GetShoppingListById,
    private val getAllShoppingLists: GetAllUserShoppingLists,
    private val createShoppingList: CreateShoppingList,
    private val editShoppingList: EditShoppingList,
    private val deleteShoppingList: DeleteShoppingList,
    private val getShoppingListItems: GetShoppingListItems
) {

    @GetMapping("/{id}")
    fun fetchOne(@PathVariable id: Long): ResponseEntity<ListSerializationDto> {
        val list = getShoppingListById(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(list.toSerialization())
    }

    @GetMapping("/{id}/items")
    fun fetchListItems(@PathVariable id: Long): ResponseEntity<List<ItemSerializationDto>> {
        val items = getShoppingListItems(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(items.map { it.toSerialization() })
    }

    @GetMapping
    fun fetchAll(): ResponseEntity<List<ListSerializationDto>> {
        val lists = getAllShoppingLists().map { it.toSerialization() }

        return ResponseEntity.ok(lists)
    }

    @PostMapping
    fun create(@Valid @RequestBody shoppingList: ListDeserializationDto): ResponseEntity<ListSerializationDto> {
        val createdList = createShoppingList(shoppingList.toDomain())

        return ResponseEntity.ok(createdList.toSerialization())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody shoppingList: ListDeserializationDto): ResponseEntity<ListSerializationDto> {
        val editedList = editShoppingList(id, shoppingList.toDomain()) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(editedList.toSerialization())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        deleteShoppingList(id) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.noContent().build()
    }
}