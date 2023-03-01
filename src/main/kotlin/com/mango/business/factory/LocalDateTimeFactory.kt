package com.mango.business.factory

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LocalDateTimeFactory {
    operator fun invoke(): LocalDateTime = LocalDateTime.now()
}
