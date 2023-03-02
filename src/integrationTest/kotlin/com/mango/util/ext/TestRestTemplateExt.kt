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
    jsonBody: String? = null,
    queryParams: Map<String, String>? = null,
): T {
    val uri = UriComponentsBuilder.fromUriString(url)
        .addQueryParams(queryParams)
        .build()
        .toUri()

    val headers = HttpHeaders().apply { contentType = MediaType.APPLICATION_JSON }
    val requestEntity = if (jsonBody != null) HttpEntity(jsonBody, headers) else null

    return exchange(
        uri,
        method,
        requestEntity,
        parameterizedTypeReference<T>(),
    ).body as T
}
