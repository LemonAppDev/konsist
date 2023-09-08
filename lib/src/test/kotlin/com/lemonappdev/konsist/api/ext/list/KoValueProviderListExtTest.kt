package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoValueProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoValueProviderListExtTest {
    @Test
    fun `withValue() returns declaration with given value`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val declaration1: KoValueProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoValueProvider = mockk {
            every { value } returns value2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withValue(value1)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withValue() returns declarations with one of given value`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val value3 = "value3"
        val declaration1: KoValueProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoValueProvider = mockk {
            every { value } returns value2
        }
        val declaration3: KoValueProvider = mockk {
            every { value } returns value3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withValue(value1, value2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutValue() returns declaration without given value`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val declaration1: KoValueProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoValueProvider = mockk {
            every { value } returns value2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutValue(value1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutValue() returns declaration without any of given value`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val value3 = "value3"
        val declaration1: KoValueProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoValueProvider = mockk {
            every { value } returns value2
        }
        val declaration3: KoValueProvider = mockk {
            every { value } returns value3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutValue(value1, value2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
