package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametersProviderListExtTest {
    @Test
    fun `withParameters() returns declaration with any parameter`() {
        // given
        val parameter: KoParameterDeclaration = mockk()
        val declaration1: KoParametersProvider = mockk {
            every { parameters } returns listOf(parameter)
        }
        val declaration2: KoParametersProvider = mockk {
            every { parameters } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameters()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParameters() returns declaration with no parameter`() {
        // given
        val parameter: KoParameterDeclaration = mockk()
        val declaration1: KoParametersProvider = mockk {
            every { parameters } returns listOf(parameter)
        }
        val declaration2: KoParametersProvider = mockk {
            every { parameters } returns emptyList()
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameters()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParameters(String) returns declaration with all of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns true
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration2: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration3: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAllParameters(parameter1, parameter2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParameters(String) returns declaration without any parameter`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns true
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration2: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration3: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAllParameters(parameter1, parameter2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSomeParameters(String) returns declaration with given parameter`() {
        // given
        val parameter = "SampleParameter"
        val declaration1: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter) } returns true
        }
        val declaration2: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParameters(parameter)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParameters(String) returns declarations with at least one of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns true
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration2: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration3: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParameters(parameter1, parameter2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParameters(String) returns declaration without given parameter`() {
        // given
        val parameter = "SampleParameter"
        val declaration1: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter) } returns true
        }
        val declaration2: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParameters(parameter)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParameters(String) returns declarations without at least one of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns true
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration2: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns true
        }
        val declaration3: KoParametersProvider = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParameters(parameter1, parameter2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }
}
