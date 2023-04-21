package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoParameter
import com.lemonappdev.konsist.testdata.SampleType
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

    @Test
    fun `withDefaultValue(name) returns parameter1 which has given default value`() {
        // given
        val value = "SampleDefaultValue"
        val parameter1: KoParameter = mockk {
            every { hasDefaultValue(value) } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasDefaultValue(value) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withDefaultValue(value)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutDefaultValue(name) returns parameter2 which has not given default value`() {
        // given
        val value = "SampleDefaultValue"
        val parameter1: KoParameter = mockk {
            every { hasDefaultValue(value) } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasDefaultValue(value) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutDefaultValue(value)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withType(name) returns parameter1 which has given type`() {
        // given
        val typeName = "SampleType"
        val parameter1: KoParameter = mockk {
            every { hasType(typeName) } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasType(typeName) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withType(typeName)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutType(name) returns parameter2 which has not given type`() {
        // given
        val typeName = "SampleType"
        val parameter1: KoParameter = mockk {
            every { hasType(typeName) } returns true
        }
        val parameter2: KoParameter = mockk {
            every { hasType(typeName) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutType(typeName)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }

    @Test
    fun `withTypeOf() returns parameter1 which has given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val parameter1: KoParameter = mockk {
            every { type.name } returns typeName1
        }
        val parameter2: KoParameter = mockk {
            every { type.name } returns typeName2
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutTypeOf() returns parameter2 which has not given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val parameter1: KoParameter = mockk {
            every { type.name } returns typeName1
        }
        val parameter2: KoParameter = mockk {
            every { type.name } returns typeName2
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }
}
