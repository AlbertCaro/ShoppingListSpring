package dev.albertocaro.shopping_list.infrastructure.user

import dev.albertocaro.shopping_list.infrastructure.common.JwtInterceptor
import dev.albertocaro.shopping_list.infrastructure.user.dto.UserDeserializationDto
import dev.albertocaro.shopping_list.infrastructure.user.dto.UserSerializationDto
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.jdbc.Sql
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForEntity
import org.springframework.web.client.postForEntity
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Sql(scripts = ["classpath:clean-db-after-test.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerTest @Autowired constructor(
    private val restTemplateBuilder: RestTemplateBuilder,
    private val jwtInterceptor: JwtInterceptor,
    @LocalServerPort private val port: Int,
) {

    private lateinit var restTemplate: RestTemplate
    private lateinit var moduleUrl: String

    @BeforeEach
    fun setUp() {
        moduleUrl = "http://localhost:$port/users"
        restTemplate = restTemplateBuilder.additionalInterceptors(jwtInterceptor).build()
    }

    @Test
    fun `should get a valid user by ID`() {
        val response = restTemplate.getForEntity<UserSerializationDto>("$moduleUrl/1")

        assertEquals(HttpStatus.OK, response.statusCode)

        assertNotNull(response.body)

        assertEquals(response.body!!.id, 1)
        assertEquals(response.body!!.username, "alberto.caro")
        assertEquals(response.body!!.name, "Alberto")
        assertEquals(response.body!!.lastName, "Caro")
        assertEquals(response.body!!.email, "alberto.caro@gmail.com")
    }

    @Test
    fun `should get the list of users`() {
        val response = restTemplate.getForEntity<List<UserSerializationDto>>(moduleUrl)

        assertEquals(HttpStatus.OK, response.statusCode)

        assertNotNull(response.body)

        assertEquals(2, response.body!!.size)
    }

    @Test
    fun `should can create a new user`() {
        val newUser = UserDeserializationDto("john.doe", "hola1234", "test@gmail.com", "John", "Doe")

        val response = restTemplate.postForEntity<UserSerializationDto>(moduleUrl, newUser)

        assertEquals(response.body!!.id, 3)
        assertEquals(response.body!!.username, "john.doe")
        assertEquals(response.body!!.name, "John")
        assertEquals(response.body!!.lastName, "Doe")
        assertEquals(response.body!!.email, "test@gmail.com")

        val getResponse = restTemplate.getForEntity<List<UserSerializationDto>>(moduleUrl)

        assertEquals(3, getResponse.body!!.size)
    }

    @Test
    fun `should can edit an existent user`() {
        val userData = UserDeserializationDto("juan.rmz", "picapapas", "juanramirez@mail.com", "Juan", "Ramirez")

        val request = HttpEntity(userData)

        val response = restTemplate.exchange<UserSerializationDto>("$moduleUrl/2", HttpMethod.PUT, request)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertNotNull(response.body)
        assertEquals(response.body!!.id, 2)
        assertEquals(response.body!!.name, userData.name)
        assertEquals(response.body!!.username, userData.username)
        assertEquals(response.body!!.email, userData.email)
        assertEquals(response.body!!.lastName, userData.lastName)

        val getListResponse = restTemplate.getForEntity<List<UserSerializationDto>>(moduleUrl)

        assertEquals(2, getListResponse.body!!.size)
    }

    @Test
    fun `should can delete an existent user`() {
        restTemplate.delete("$moduleUrl/2")

        assertThrows<HttpClientErrorException> {
            val response = restTemplate.getForEntity<UserSerializationDto>("$moduleUrl/2")

            assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
        }

        val listResponse = restTemplate.getForEntity<List<UserSerializationDto>>(moduleUrl)

        assertEquals(1, listResponse.body!!.size)
    }
}