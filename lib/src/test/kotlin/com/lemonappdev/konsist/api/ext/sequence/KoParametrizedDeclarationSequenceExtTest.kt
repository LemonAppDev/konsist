package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.ext.sequence.withParameter
import com.lemonappdev.konsist.api.ext.sequence.withParameters
import com.lemonappdev.konsist.api.ext.sequence.withSomeParameters
import com.lemonappdev.konsist.api.ext.sequence.withoutParameter
import com.lemonappdev.konsist.api.ext.sequence.withoutParameters
import com.lemonappdev.konsist.core.declaration.KoParametrizedDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametrizedDeclarationSequenceExtTest {
    @Test
    fun `withParameters() returns parametrizedDeclaration1 which has parameters`() {
        // given
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed() } returns true
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed() } returns false
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2)

        // when
        val sut = parametrizedDeclarations.withParameter()

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration1)
    }

    @Test
    fun `withoutParameters() returns parametrizedDeclaration2 which has not parameters`() {
        // given
        val parametrizedDeclaration1: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed() } returns true
        }
        val parametrizedDeclaration2: KoParametrizedDeclarationImpl = mockk {
            every { hasParameterNamed() } returns false
        }
        val parametrizedDeclarations = sequenceOf(parametrizedDeclaration1, parametrizedDeclaration2)

        // when
        val sut = parametrizedDeclarations.withoutParameter()

        // then
        sut.toList() shouldBeEqualTo listOf(parametrizedDeclaration2)
    }

    @Test
    fun `withParameters(String) returns parametrizedDeclaration1 with given parameters`() {
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
    fun `withoutParameters(String) returns parametrizedDeclaration3 without given parameters`() {
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
    fun `withSomeParameters(String) returns parametrizedDeclarations which have at least one of given parameters`() {
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
