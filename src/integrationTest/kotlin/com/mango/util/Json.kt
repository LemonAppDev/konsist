package com.mango.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature

object Json {
    private val objectMapper = ObjectMapper().apply {
        // Register required modules to enable serialisation for various data types e.g. LocalDateTime, Kotlin Value Class...
        findAndRegisterModules()

        // LocalDateTime values are serialised to ISO-8601 format by default.
        // before: [2023,3,14,12,34,58,783185000]
        // after: "2023-03-14T12:32:58.214549"
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    fun encodeToString(jsonBody: Any?): String? = objectMapper.writeValueAsString(jsonBody)
}
