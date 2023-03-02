package com.mango.util

import com.fasterxml.jackson.databind.ObjectMapper

object Json {
    private val objectMapper = ObjectMapper()
    fun encodeToString(jsonBody: Any?): String? {
        // Register required modules e.g. LocalDateTime or Kotlin Value Class
        objectMapper.findAndRegisterModules()
        return objectMapper.writeValueAsString(jsonBody)
    }
}
