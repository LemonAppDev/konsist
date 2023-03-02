package com.mango.util

import org.springframework.core.ParameterizedTypeReference

inline fun <reified T> parameterizedTypeReference() = object : ParameterizedTypeReference<T>() {}
