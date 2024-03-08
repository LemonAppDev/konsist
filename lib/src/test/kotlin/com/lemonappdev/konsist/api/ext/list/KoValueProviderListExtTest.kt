package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoValueProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoValueProviderListExtTest {
    @Test
    fun `withValue() returns declaration with any value`() {
        // given
        val declaration1: KoValueProvider =
            mockk {
                every { hasValue() } returns true
            }
        val declaration2: KoValueProvider =
            mockk {
                every { hasValue() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withValue()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutValue() returns declaration without value`() {
        // given
        val declaration1: KoValueProvider =
            mockk {
                every { hasValue() } returns true
            }
        val declaration2: KoValueProvider =
            mockk {
                every { hasValue() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutValue()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withValue() returns declarations with one of given value`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val declaration1: KoValueProvider =
            mockk {
                every { hasValue(value1) } returns true
                every { hasValue(value2) } returns false
            }
        val declaration2: KoValueProvider =
            mockk {
                every { hasValue(value1) } returns false
                every { hasValue(value2) } returns true
            }
        val declaration3: KoValueProvider =
            mockk {
                every { hasValue(value1) } returns false
                every { hasValue(value2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withValue(value1, value2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutValue() returns declaration without any of given value`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val declaration1: KoValueProvider =
            mockk {
                every { hasValue(value1) } returns true
                every { hasValue(value2) } returns false
            }
        val declaration2: KoValueProvider =
            mockk {
                every { hasValue(value1) } returns false
                every { hasValue(value2) } returns true
            }
        val declaration3: KoValueProvider =
            mockk {
                every { hasValue(value1) } returns false
                every { hasValue(value2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutValue(value1, value2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withValue{predicate} returns declaration with value matching to predicate`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val predicate: (String) -> Boolean = { it == value1 }
        val declaration1: KoValueProvider =
            mockk {
                every { value } returns value1
            }
        val declaration2: KoValueProvider =
            mockk {
                every { value } returns value2
            }
        val declaration3: KoValueProvider =
            mockk {
                every { value } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withValue(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutValue{predicate} returns declaration without value matching to predicate`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val predicate: (String) -> Boolean = { it == value1 }
        val declaration1: KoValueProvider =
            mockk {
                every { value } returns value1
            }
        val declaration2: KoValueProvider =
            mockk {
                every { value } returns value2
            }
        val declaration3: KoValueProvider =
            mockk {
                every { value } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutValue(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }
}
