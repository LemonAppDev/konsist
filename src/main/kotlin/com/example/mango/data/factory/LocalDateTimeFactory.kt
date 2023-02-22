package com.example.mango.data.factory

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LocalDateTimeFactory {
    fun create(): LocalDateTime = LocalDateTime.now()
}
