package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoRepresentsTypeProviderSequenceExtTest {
    @Test
    fun `withRepresentedType(String) returns declaration with given type`() {
        // given
        val type = "type"
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type) } returns true
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withRepresentedType(type)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withRepresentedType(String) returns declarations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val declaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withRepresentedType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutRepresentedType(String) returns declaration without given type`() {
        // given
        val type1 = "type1"
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedType(type1)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutRepresentedType(String) returns declaration without any of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val declaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutRepresentedType(type1, type2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withRepresentedTypeOf(KClass) returns declarations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val declaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withRepresentedTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutRepresentedTypeOf(KClass) returns declaration without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns true
            every { representsType(type2) } returns false
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns true
        }
        val declaration3: KoRepresentsTypeProvider = mockk {
            every { representsType(type1) } returns false
            every { representsType(type2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutRepresentedTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration3)
    }

    //  'every { representsType<SampleClass>() } returns true' doesn't work because there is a bug in mockk
    @Test
    fun `withRepresentedTypeOf() with KClass syntax returns declaration with SampleClass type`() {
        // given
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { SampleClass::class.simpleName?.let { representsType(it) } } returns true
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { SampleClass::class.simpleName?.let { representsType(it) } } returns false
            every { SampleClass::class.qualifiedName?.let { representsType(it) } } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withRepresentedTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutRepresentedTypeOf() with KClass syntax returns declaration without SampleClass type`() {
        // given
        val declaration1: KoRepresentsTypeProvider = mockk {
            every { SampleClass::class.simpleName?.let { representsType(it) } } returns true
        }
        val declaration2: KoRepresentsTypeProvider = mockk {
            every { SampleClass::class.simpleName?.let { representsType(it) } } returns false
            every { SampleClass::class.qualifiedName?.let { representsType(it) } } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedTypeOf<SampleClass>()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
