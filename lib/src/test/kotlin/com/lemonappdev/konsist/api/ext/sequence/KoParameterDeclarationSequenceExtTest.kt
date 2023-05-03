package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationSequenceExtTest {
    @Test
    fun `withVarargModifier() returns parameter1 with vararg modifier`() {
        // given
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasVarargModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
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
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasVarargModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
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
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasNoInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
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
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasNoInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
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
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasCrossInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
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
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { hasCrossInlineModifier() } returns true
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
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
    fun `withoutDefaultValue() returns parameter2 which has not default value`() {
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
    fun `withDefaultValue(name) returns parameters which have one of given default values`() {
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
    fun `withoutDefaultValue(name) returns parameter3 which has not any given default value`() {
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

    @Test
    fun `withType(name) returns parameters which have one of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns true
            every { representsType(typeName2) } returns false
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns true
        }
        val parameter3: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2, parameter3)

        // when
        val sut = parameters.withType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1, parameter2)
    }

    @Test
    fun `withoutType(name) returns parameter3 which has not any given type`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns true
            every { representsType(typeName2) } returns false
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns true
        }
        val parameter3: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2, parameter3)

        // when
        val sut = parameters.withoutType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter3)
    }

    @Test
    fun `withTypeOf(KClass) returns parameters which have one of given types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns true
            every { representsType(typeName2) } returns false
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns true
        }
        val parameter3: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2, parameter3)

        // when
        val sut = parameters.withTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1, parameter2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns parameter3 which has not any given type`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns true
            every { representsType(typeName2) } returns false
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns true
        }
        val parameter3: KoParameterDeclarationImpl = mockk {
            every { representsType(typeName1) } returns false
            every { representsType(typeName2) } returns false
        }
        val parameters = sequenceOf(parameter1, parameter2, parameter3)

        // when
        val sut = parameters.withoutTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter3)
    }

    @Test
    fun `withTypeOf() returns parameter1 which has given type`() {
        // given
        val typeName1 = "SampleType"
        val typeName2 = "OtherType"
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { type.name } returns typeName1
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
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
        val parameter1: KoParameterDeclarationImpl = mockk {
            every { type.name } returns typeName1
        }
        val parameter2: KoParameterDeclarationImpl = mockk {
            every { type.name } returns typeName2
        }
        val parameters = sequenceOf(parameter1, parameter2)

        // when
        val sut = parameters.withoutTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }
}
