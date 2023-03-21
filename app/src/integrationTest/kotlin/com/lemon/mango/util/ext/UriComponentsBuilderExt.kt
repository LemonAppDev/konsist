package com.lemon.mango.util.ext

import org.springframework.web.util.UriComponentsBuilder

fun UriComponentsBuilder.addQueryParams(queryParams: Map<String, String>?): UriComponentsBuilder {
    queryParams?.forEach { (key, value) -> queryParam(key, value) }
    return this
}
