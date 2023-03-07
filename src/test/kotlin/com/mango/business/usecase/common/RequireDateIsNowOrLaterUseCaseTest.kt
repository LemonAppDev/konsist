package com.mango.business.usecase.common

import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.Month

class RequireDateIsNowOrLaterUseCaseTest {
    private val sut = RequireDateIsNowOrLaterUseCase()

    @Test
    fun `throws exception when given date is in the past`() {
        // given
        val currentDate = LocalDateTime.of(2023, Month.MARCH, 20, 21, 0, 0, 0)
        val givenDate = LocalDateTime.of(2023, Month.FEBRUARY, 20, 21, 0, 0, 0)

        // when
        val actual = { sut(givenDate, currentDate) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Given date is in the past: $givenDate"
    }
}
