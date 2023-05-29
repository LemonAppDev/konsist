package com.lemonappdev.konsist.api.ext.sequence.koparameterdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withType
import com.lemonappdev.konsist.api.ext.sequence.withTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutType
import com.lemonappdev.konsist.api.ext.sequence.withoutTypeOf
import com.lemonappdev.konsist.core.declaration.KoParameterDeclarationImpl
import com.lemonappdev.konsist.testdata.SampleType
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForTypeSequenceExtTest {
    @Test
    fun `withType(name) returns parameters with one of given types`() {
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
    fun `withoutType(name) returns parameter without any of given types`() {
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
    fun `withTypeOf(KClass) returns parameters with one of given types`() {
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
    fun `withoutTypeOf(KClass) returns parameter without any of given types`() {
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
    fun `withTypeOf() returns parameter with given type`() {
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
    fun `withoutTypeOf() returns parameter without given type`() {
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
