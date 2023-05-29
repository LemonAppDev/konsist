package com.lemonappdev.konsist.api.ext.sequence.koparameterdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withDefaultValue
import com.lemonappdev.konsist.api.ext.sequence.withoutDefaultValue
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForDefaultValueSequenceExtTest {
    @Test
    fun `withDefaultValue() returns parameter with default value`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withDefaultValue()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutDefaultValue() returns parameter without default value`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue() } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutDefaultValue()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withDefaultValue(name) returns parameters with one of given default values`() {
        // given
        val value1 = "SampleDefaultValue1"
        val value2 = "SampleDefaultValue2"
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue(value1) } returns true
            every { hasDefaultValue(value2) } returns false
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue(value1) } returns false
            every { hasDefaultValue(value2) } returns true
        }
        val parameter3: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue(value1) } returns false
            every { hasDefaultValue(value2) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2, parameter3)

        // when
        val sut = parameters.withDefaultValue(value1, value2)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1, parameter2)
    }

    @Test
    fun `withoutDefaultValue(name) returns parameter without any of given default values`() {
        // given
        val value1 = "SampleDefaultValue1"
        val value2 = "SampleDefaultValue2"
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue(value1) } returns true
            every { hasDefaultValue(value2) } returns false
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue(value1) } returns false
            every { hasDefaultValue(value2) } returns true
        }
        val parameter3: KoParameterDeclarationImpl = mockk {
            every { hasDefaultValue(value1) } returns false
            every { hasDefaultValue(value2) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2, parameter3)

        // when
        val sut = parameters.withoutDefaultValue(value1, value2)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter3)
    }
}
