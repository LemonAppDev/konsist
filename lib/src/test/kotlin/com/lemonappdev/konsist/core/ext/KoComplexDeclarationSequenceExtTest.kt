package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationSequenceExtTest {
    @Test
    fun `withType(String) returns complex declarations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1, complexDeclaration2)
    }

    @Test
    fun `withoutType(String) returns complex declaration without any given type`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withoutType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration3)
    }

    @Test
    fun `withTypeOf(KClass) returns complex declarations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1, complexDeclaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns complex declaration without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val complexDeclaration3: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2, complexDeclaration3)

        // when
        val sut = complexDeclarations.withoutTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration3)
    }

    //  'every { representsType<SampleClass>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withTypeOf() with KClass syntax returns SampleClass`() {
        // given
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1)
    }

    @Test
    fun `withoutTypeOf() with KClass syntax returns complex declaration without SampleClass`() {
        // given
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withoutTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration2)
    }
}
