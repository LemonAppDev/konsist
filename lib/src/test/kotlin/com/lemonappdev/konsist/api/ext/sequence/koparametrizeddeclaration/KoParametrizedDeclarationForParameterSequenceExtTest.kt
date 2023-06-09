package com.lemonappdev.konsist.api.ext.sequence.koparametrizeddeclaration

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.ext.sequence.withParameters
import com.lemonappdev.konsist.api.ext.sequence.withSomeParameters
import com.lemonappdev.konsist.api.ext.sequence.withoutParameters
import com.lemonappdev.konsist.core.declaration.KoParametrizedDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametrizedDeclarationForParameterSequenceExtTest {
    @Test
    fun `withParameters(String) returns true if has parameters`() {
        // given
        val parameter: KoParameterDeclaration = mockk()
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { parameters } returns listOf(parameter)
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { parameters } returns emptyList()
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2)

        // when
        val sut = parametrizedDeclarations.withParameters()

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration1)
    }

    @Test
    fun `withParameters(String) returns parametrized declaration with all of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns true
            every { hasParameterNamed(parameter2) } returns true
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns true
        }
        val parametrizedDeclaration3: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns false
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2, parametrizedDeclaration3)

        // when
        val sut = parametrizedDeclarations.withParameters(parameter1, parameter2)

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration1)
    }

    @Test
    fun `withoutParameters(String) returns parametrized declaration without given parameter`() {
        // given
        val parameter: KoParameterDeclaration = mockk()
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { parameters } returns listOf(parameter)
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { parameters } returns emptyList()
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2)

        // when
        val sut = parametrizedDeclarations.withoutParameters()

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration2)
    }

    @Test
    fun `withoutParameters(String) returns parametrized declaration without any of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns true
            every { hasParameterNamed(parameter2) } returns true
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns true
        }
        val parametrizedDeclaration3: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns false
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2, parametrizedDeclaration3)

        // when
        val sut = parametrizedDeclarations.withoutParameters(parameter1, parameter2)

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration3)
    }

    @Test
    fun `withSomeParameters(String) returns parametrized declaration with given parameter`() {
        // given
        val parameter = "SampleParameter"
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter) } returns true
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter) } returns false
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2)

        // when
        val sut = parametrizedDeclarations.withSomeParameters(parameter)

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration1)
    }

    @Test
    fun `withSomeParameters(String) returns parametrized declarations with at least one of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns true
            every { hasParameterNamed(parameter2) } returns true
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns true
        }
        val parametrizedDeclaration3: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed(parameter1) } returns false
            every { hasParameterNamed(parameter2) } returns false
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2, parametrizedDeclaration3)

        // when
        val sut = parametrizedDeclarations.withSomeParameters(parameter1, parameter2)

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration1, parametrizedDeclaration2)
    }
}
