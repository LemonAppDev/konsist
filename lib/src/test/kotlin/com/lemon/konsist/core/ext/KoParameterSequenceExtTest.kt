package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoParameter
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterSequenceExtTest {
    @Test
    fun `withVarargModifier() returns parameter1 with vararg modifier`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasVarargModifier() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasVarargModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withVarargModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutVarargModifier() returns parameter2 without vararg modifier`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasVarargModifier() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasVarargModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutVarargModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withNoInlineModifier() returns parameter1 with noInline modifier`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasNoInlineModifier() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasNoInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withNoInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutNoInlineModifier() returns parameter2 without noInline modifier`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasNoInlineModifier() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasNoInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutNoInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withCrossInlineModifier() returns parameter1 with crossInline modifier`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasCrossInlineModifier() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasCrossInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withCrossInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutCrossInlineModifier() returns parameter2 without crossInline modifier`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasCrossInlineModifier() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasCrossInlineModifier() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutCrossInlineModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withDefaultValue() returns parameter1 which has default value`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasDefaultValue() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasDefaultValue() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withDefaultValue()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutDefaultValue() returns parameter2 which has not default value`() {
        // given
        val parameter1: KoParameter = mockk {
            every { hasDefaultValue() } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasDefaultValue() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutDefaultValue()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }
}