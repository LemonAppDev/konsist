package com.mango.domain.common.usecase

import com.mango.domain.common.LocalDateTimeFactory
import com.mango.domain.common.model.BusinessTestModel.getCurrentDate
import com.mango.domain.common.model.BusinessTestModel.getPastDate
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldThrow
import org.amshove.kluent.withMessage
import org.junit.jupiter.api.Test

class RequireDateIsNowOrLaterUseCaseTest {
    private val localDateTimeFactory: LocalDateTimeFactory = mockk()

    private val sut = RequireDateIsNowOrLaterUseCase(
        localDateTimeFactory,
    )

    @Test
    fun `throws exception when given date is in the past`() {
        // given
        val currentDate = getCurrentDate()
        every { localDateTimeFactory() } returns currentDate
        val givenDate = getPastDate()

        // when
        val actual = { sut(givenDate) }

        // then
        actual shouldThrow IllegalArgumentException::class withMessage "Given date is in the past: $givenDate"
    }
}
