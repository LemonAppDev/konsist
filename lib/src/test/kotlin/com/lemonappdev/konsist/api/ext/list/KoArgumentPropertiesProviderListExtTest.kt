package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoArgumentPropertiesProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentPropertiesProviderListExtTest {
    @Test
    fun `withArgumentName() returns declaration with given argument name`() {
        // given
        val argumentName1 = "name1"
        val argumentName2 = "name2"
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArgumentName(argumentName1)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withArgumentName() returns declarations with one of given names`() {
        // given
        val argumentName1 = "name1"
        val argumentName2 = "name2"
        val argumentName3 = "name3"
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName2
        }
        val declaration3: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withArgumentName(argumentName1, argumentName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutArgumentName() returns declaration without given argument name`() {
        // given
        val argumentName1 = "name1"
        val argumentName2 = "name2"
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArgumentName(argumentName1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutArgumentName() returns declaration without any of given argument name`() {
        // given
        val argumentName1 = "name1"
        val argumentName2 = "name2"
        val argumentName3 = "name3"
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName2
        }
        val declaration3: KoArgumentPropertiesProvider = mockk {
            every { argumentName } returns argumentName3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutArgumentName(argumentName1, argumentName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withValue() returns declaration with given value`() {
        // given
        val value1 = "value1"
        val value2 = "value2"
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
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
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
            every { value } returns value2
        }
        val declaration3: KoArgumentPropertiesProvider = mockk {
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
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
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
        val declaration1: KoArgumentPropertiesProvider = mockk {
            every { value } returns value1
        }
        val declaration2: KoArgumentPropertiesProvider = mockk {
            every { value } returns value2
        }
        val declaration3: KoArgumentPropertiesProvider = mockk {
            every { value } returns value3
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutValue(value1, value2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
