package com.mango.util

import com.mango.application.config.ApiConfig
import com.mango.util.ext.exchange
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class ControllerEndpointCaller {
    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Autowired
    lateinit var servletWebServerApplicationContext: ServletWebServerApplicationContext

    final inline fun <reified T : Any?> call(
        controllerTestInstance: Any,
        endpointName: String,
        method: HttpMethod,
        body: String? = null,
        queryParams: Map<String, String>? = null,
    ) = testRestTemplate.exchange<T>(
        url = getEndpointUrl(controllerTestInstance::class, endpointName),
        method = method,
        queryParams = queryParams,
        body = body,
    )

    fun getEndpointUrl(controllerTestHelperKClass: KClass<out Any>, endpointName: String): String {
        val port = servletWebServerApplicationContext.webServer.port
        val simpleName = controllerTestHelperKClass.simpleName
        val suffix = "EndpointHelper"
        requireNotNull(simpleName?.endsWith(suffix)) {
            "Controller test helper class simple name must end with 'ControllerTestHelper'. Actual simple name: $simpleName"
        }

        val controllerName = simpleName?.substringBefore(suffix)?.lowercase()

        requireNotNull(controllerName) { "Controller name must not be null" }
        return "http://localhost:$port${ApiConfig.API_V1_URL}/$controllerName/$endpointName"
    }
}
