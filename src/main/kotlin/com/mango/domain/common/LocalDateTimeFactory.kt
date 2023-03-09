package com.mango.domain.common

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LocalDateTimeFactory {
    operator fun invoke(): LocalDateTime = LocalDateTime.now()
}
