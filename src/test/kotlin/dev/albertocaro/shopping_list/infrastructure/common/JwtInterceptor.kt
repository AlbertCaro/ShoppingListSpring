package dev.albertocaro.shopping_list.infrastructure.common

import dev.albertocaro.shopping_list.core.AuthService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Service

@Service
class JwtInterceptor(
    private val authService: AuthService
) : ClientHttpRequestInterceptor {
    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution,
    ): ClientHttpResponse {
        val token = authService.generateToken(1)

        val headers = request.headers
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer $token")
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())

        return execution.execute(request, body)
    }
}