package com.mango.util

import com.mango.util.ext.exchange
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class ControllerEndpointCaller(
    private val servletWebServerApplicationContext: ServletWebServerApplicationContext,
) {
    final inline fun <reified T : Any?> call(
        controllerTestInstance: Any,
        testRestTemplate: TestRestTemplate,
        endpointName: String,
        method: HttpMethod,
        jsonBody: String? = null,
        queryParams: Map<String, String>? = null,
    ): T {
        return testRestTemplate.exchange(
            url = getEndpointUrl(controllerTestInstance::class, endpointName),
            method = method,
            jsonBody = jsonBody,
            queryParams = queryParams,
        )
    }

    fun getEndpointUrl(controllerKClass: KClass<out Any>, endpointName: String): String {
        val port = servletWebServerApplicationContext.webServer.port
        val controllerTestSimpleName = controllerKClass.simpleName
        requireNotNull(controllerTestSimpleName?.endsWith("ControllerTest")) {
            "Controller test class simple name must end with 'ControllerTest'. Actual simple name: $controllerTestSimpleName"
        }

        val controllerName = controllerTestSimpleName?.substringBefore("Controller")?.lowercase()

        requireNotNull(controllerName) { "Controller name must not be null" }
        return "http://localhost:$port/v1/$controllerName/$endpointName"
    }
}
