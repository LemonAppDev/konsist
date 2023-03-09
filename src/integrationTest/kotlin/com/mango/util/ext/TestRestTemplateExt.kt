package com.mango.util.ext

import com.mango.util.parameterizedTypeReference
import org.springframework.boot.test.web.client.TestRestTemplate
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

    val responseEntity = exchange(
        uri,
        method,
        requestEntity,
        parameterizedTypeReference<T>(),
    )

    val errorLogLine = "\n\terror: ${responseEntity.statusCode}\n\tmethod:$method"
    val queryParamsLogLine = "\n\tqueryParams: $queryParams"
    val jsonBodyLogLine = "\n\trequestBody: $body"
    val emptyLogLine = "\n"

    if (responseEntity.statusCode.isError) {
        val message = "Error calling $url$errorLogLine$queryParamsLogLine$jsonBodyLogLine$emptyLogLine"

        throw (RuntimeException(message))
    } else {
        val jsonResponseLogLine = "\n\tresponseBody: ${responseEntity.body}"
        val message = "Success calling $url$errorLogLine$queryParamsLogLine$jsonBodyLogLine$jsonResponseLogLine$emptyLogLine"
        println(message)
    }

    return responseEntity.body as T
}
