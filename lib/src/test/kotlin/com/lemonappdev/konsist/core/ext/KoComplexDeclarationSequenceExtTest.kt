package com.lemonappdev.konsist.core.ext

import com.lemonappdev.konsist.core.declaration.KoComplexDeclaration
import com.lemonappdev.konsist.testdata.SampleClass
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoComplexDeclarationSequenceExtTest {
    @Test
    fun `withType() returns complex declaration with type type1`() {
        // given
        val type1 = "type1"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1)
    }

    @Test
    fun `withoutType() returns complex declaration without type type1`() {
        // given
        val type1 = "type1"
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns true
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { representsType(type1) } returns false
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withoutType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration2)
    }

    //  'every { representsType<SampleClass>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withType() with KClass syntax returns SampleClass`() {
        // given
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withType<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration1)
    }

    @Test
    fun `withoutType() with KClass syntax returns complex declaration without SampleClass`() {
        // given
        val complexDeclaration1: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.SampleClass"
        }
        val complexDeclaration2: KoComplexDeclaration = mockk {
            every { fullyQualifiedName } returns "com.lemonappdev.konsist.testdata.NonExistingClass"
        }
        val complexDeclarations = sequenceOf(complexDeclaration1, complexDeclaration2)

        // when
        val sut = complexDeclarations.withoutType<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(complexDeclaration2)
    }
}
