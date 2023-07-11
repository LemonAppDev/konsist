package com.lemonappdev.konsist.api.ext.sequence.kocomplexdeclaration

import com.lemonappdev.konsist.api.ext.sequence.withRepresentedType
import com.lemonappdev.konsist.api.ext.sequence.withRepresentedTypeOf
import com.lemonappdev.konsist.api.ext.sequence.withoutRepresentedType
import com.lemonappdev.konsist.api.ext.sequence.withoutRepresentedTypeOf
import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationForTypeSequenceExtTest {
    @Test
    fun `withRepresentedType(String) returns complex declaration with given type`() {
        // given
        val type = "type"
        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type) } returns true
        }
        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withRepresentedType(type)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1)
    }

    @Test
    fun `withRepresentedType(String) returns complex declarations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withRepresentedType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1, complexDeclaration2)
    }

    @Test
    fun `withoutRepresentedType(String) returns complex declaration without given type`() {
        // given
        val type1 = "type1"
        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
        }
        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withoutRepresentedType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration2)
    }

    @Test
    fun `withoutRepresentedType(String) returns complex declaration without any of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withoutRepresentedType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration3)
    }

    @Test
    fun `withRepresentedTypeOf(KClass) returns complex declarations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withRepresentedTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1, complexDeclaration2)
    }

    @Test
    fun `withoutRepresentedTypeOf(KClass) returns complex declaration without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withoutRepresentedTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration3)
    }

    //  'every { representsType<SampleClass>() } returns true' doesn't work because there is a bug in mockk
    // ToDo("uncomment this tests after fix bug with withRepresentsTypeOf")
//    @Test
//    fun `withRepresentedTypeOf() with KClass syntax returns complex declaration with SampleClass type`() {
//        // given
//        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
//            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
//        }
//        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
//            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
//        }
//        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)
//
//        // when
//        val sut = complexDeclarations.withRepresentedTypeOf<SampleClass>()
//
//        // then
//        sut.toList() shouldBeEqualTo listOf(complexDeclaration1)
//    }
//
//    @Test
//    fun `withoutRepresentedTypeOf() with KClass syntax returns complex declaration without SampleClass type`() {
//        // given
//        val complexDeclaration1: KoRepresentsTypeProvider = mockk {
//            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
//        }
//        val complexDeclaration2: KoRepresentsTypeProvider = mockk {
//            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
//        }
//        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)
//
//        // when
//        val sut = complexDeclarations.withoutRepresentedTypeOf<SampleClass>()
//
//        // then
//        sut.toList() shouldBeEqualTo listOf(complexDeclaration2)
//    }
}
