package dev.albertocaro.shopping_list.infrastructure.user

import dev.albertocaro.shopping_list.infrastructure.user.dto.UserSerializationDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest @Autowired constructor(
    private val restTemplateBuilder: RestTemplateBuilder,
    @LocalServerPort private val port: Int,
) {

    private lateinit var restTemplate: RestTemplate
    private lateinit var moduleUrl: String

    @BeforeEach
    fun setUp() {
        moduleUrl = "http://localhost:$port/api/users"
        restTemplate = restTemplateBuilder.build()
    }

    @Test
    fun `should get a valid user by ID`() {
        val reponse = restTemplate.getForEntity<UserSerializationDto>("$moduleUrl/1")

        assertEquals(HttpStatus.OK, reponse.statusCode)

        assertNotNull(reponse.body)

        assertEquals(reponse.body!!.id, 1)
        assertEquals(reponse.body!!.username, "alberto.caro")
        assertEquals(reponse.body!!.name, "Alberto")
        assertEquals(reponse.body!!.lastName, "Caro")
        assertEquals(reponse.body!!.email, "alberto.caro@gmail.com")
    }
}