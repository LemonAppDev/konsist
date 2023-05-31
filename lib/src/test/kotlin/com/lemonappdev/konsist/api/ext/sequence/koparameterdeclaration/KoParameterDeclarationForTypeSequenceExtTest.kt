package com.lemonappdev.konsist.api.ext.sequence.koparameterdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withRepresentedType
import com.lemonappdev.konsist.api.ext.sequence.withRepresentedTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutRepresentedType
import com.lemonappdev.konsist.api.ext.sequence.withoutRepresentedTypeOf
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
    fun `withRepresentedType(name) returns parameters with one of given types`() {
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
        val sut = parameters.withRepresentedType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1, parameter2)
    }

    @Test
    fun `withoutRepresentedType(name) returns parameter without any of given types`() {
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
        val sut = parameters.withoutRepresentedType(typeName1, typeName2)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter3)
    }

    @Test
    fun `withRepresentedTypeOf(KClass) returns parameters with one of given types`() {
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
        val sut = parameters.withRepresentedTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1, parameter2)
    }

    @Test
    fun `withoutRepresentedTypeOf(KClass) returns parameter without any of given types`() {
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
        val sut = parameters.withoutRepresentedTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(parameter3)
    }

    @Test
    fun `withRepresentedTypeOf() returns parameter with given type`() {
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
        val sut = parameters.withRepresentedTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter1)
    }

    @Test
    fun `withoutRepresentedTypeOf() returns parameter without given type`() {
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
        val sut = parameters.withoutRepresentedTypeOf<SampleType>()

        // then
        sut.toList() shouldBeEqualTo listOf(parameter2)
    }
}
