package com.mango.util.ext

import com.fasterxml.jackson.core.type.TypeReference
import com.mango.util.Json.deserialize
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.util.UriComponentsBuilder

inline fun <reified T : Any?> TestRestTemplate.exchange(
    url: String,
    method: HttpMethod,
    queryParams: Map<String, String>? = null,
    body: String? = null,
): T {
    val uri = UriComponentsBuilder.fromUriString(url)
        .addQueryParams(queryParams)
        .build()
        .toUri()

    val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
    val requestEntity = if (body != null) HttpEntity(body, headers) else null

    // parameterizedTypeReference<String> is passed instead of parameterizedTypeReference<T> to
    // allow logging JSON response body (otherwise body would contain parsed type, not JSON string)
    val responseEntity = exchange(
        uri,
        method,
        requestEntity,
        parameterizedTypeReference<String>(),
    )

    val responseCodeLogLine = "\n\tresponse code: ${responseEntity.statusCode}\n\tmethod:$method"
    val queryParamsLogLine = "\n\tqueryParams: $queryParams"
    val jsonBodyLogLine = "\n\trequestBody: $body"
    val emptyLogLine = "\n"

    if (responseEntity.statusCode.isError) {
        val message = "Error calling $url$responseCodeLogLine$queryParamsLogLine$jsonBodyLogLine$emptyLogLine"

        throw (RuntimeException(message))
    } else {
        val jsonResponseLogLine = "\n\tresponseBody: ${responseEntity.body}"
        val message = "Success calling $url$responseCodeLogLine$queryParamsLogLine$jsonBodyLogLine$jsonResponseLogLine$emptyLogLine"
        println(message)
    }

    // typeReference is used to allow parsing nested generic types e.g. List<Task>
    return deserialize(responseEntity.body, typeReference())
}

inline fun <reified T> parameterizedTypeReference() = object : ParameterizedTypeReference<T>() {}
inline fun <reified T> typeReference() = object : TypeReference<T>() {}
