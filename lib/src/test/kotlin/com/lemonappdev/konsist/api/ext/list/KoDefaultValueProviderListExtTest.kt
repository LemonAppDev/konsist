package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoDefaultValueProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDefaultValueProviderListExtTest {
    @Test
    fun `withDefaultValue() returns declaration with default value`() {
        // given
        val declaration1: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue() } returns true
            }
        val declaration2: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDefaultValue()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDefaultValue() returns declaration without default value`() {
        // given
        val declaration1: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue() } returns true
            }
        val declaration2: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDefaultValue()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withDefaultValue(name) returns declarations with one of given default values`() {
        // given
        val value1 = "SampleDefaultValue1"
        val value2 = "SampleDefaultValue2"
        val declaration1: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue(value1) } returns true
                every { hasDefaultValue(value2) } returns false
            }
        val declaration2: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue(value1) } returns false
                every { hasDefaultValue(value2) } returns true
            }
        val declaration3: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue(value1) } returns false
                every { hasDefaultValue(value2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withDefaultValue(value1, value2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutDefaultValue(name) returns declaration without any of given default values`() {
        // given
        val value1 = "SampleDefaultValue1"
        val value2 = "SampleDefaultValue2"
        val declaration1: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue(value1) } returns true
                every { hasDefaultValue(value2) } returns false
            }
        val declaration2: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue(value1) } returns false
                every { hasDefaultValue(value2) } returns true
            }
        val declaration3: KoDefaultValueProvider =
            mockk {
                every { hasDefaultValue(value1) } returns false
                every { hasDefaultValue(value2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutDefaultValue(value1, value2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
