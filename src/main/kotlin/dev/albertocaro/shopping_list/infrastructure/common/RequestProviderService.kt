package dev.albertocaro.shopping_list.infrastructure.common

import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Service
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

@Service
class RequestProviderService {

    fun getRequest(): HttpServletRequest? {
        val requestAttributes = RequestContextHolder.getRequestAttributes() as? ServletRequestAttributes

        return requestAttributes?.request
    }
}