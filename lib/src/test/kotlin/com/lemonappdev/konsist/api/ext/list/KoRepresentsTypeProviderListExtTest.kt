package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoRepresentsTypeProvider
import com.lemonappdev.konsist.testdata.SampleClass1
import com.lemonappdev.konsist.testdata.SampleClass2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoRepresentsTypeProviderListExtTest {
    @Test
    fun `withRepresentedType(String) returns declaration with given type`() {
        // given
        val type = "type"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns true
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withRepresentedType(type)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withRepresentedType(String) returns declarations with one of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns true
            }
        val declaration3: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withRepresentedType(type1, type2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withRepresentedType(null) returns empty list`() {
        // given
        val type = null
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns false
            }

        val declarations = listOf(declaration1)

        // when
        val sut = declarations.withRepresentedType(type)

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withRepresentedType(String, null) returns empty list when both dont match`() {
        // given
        val type1 = "type"
        val type2 = null
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }

        val declarations = listOf(declaration1)

        // when
        val sut = declarations.withRepresentedType(type1, type2)

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withRepresentedType(null, String) returns empty list when both dont match`() {
        // given
        val type1 = null
        val type2 = "type"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }

        val declarations = listOf(declaration1)

        // when
        val sut = declarations.withRepresentedType(type1, type2)

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutRepresentedType(String) returns declaration without given type`() {
        // given
        val type1 = "type1"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedType(type1)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutRepresentedType(String) returns declaration without any of given types`() {
        // given
        val type1 = "type1"
        val type2 = "type2"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns true
            }
        val declaration3: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutRepresentedType(type1, type2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutRepresentedType(null) returns all given declarations`() {
        // given
        val type1 = null
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedType(type1)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutRepresentedType(String, null) returns declaration without any of given types`() {
        // given
        val type1 = "type1"
        val type2 = null
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedType(type1, type2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutRepresentedType(null, String) returns declaration without any of given types`() {
        // given
        val type1 = null
        val type2 = "type1"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedType(type1, type2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withRepresentedTypeOf(KClass) returns declaration with given type`() {
        // given
        val type = "com.lemonappdev.konsist.testdata.SampleClass1"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns true
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withRepresentedTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withRepresentedTypeOf(KClass) returns declarations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns true
            }
        val declaration3: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withRepresentedTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withRepresentedTypeOf(null) returns empty list`() {
        // given
        val type = null
        val declaration: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns false
            }

        val declarations = listOf(declaration)

        // when
        val sut = declarations.withRepresentedTypeOf(type)

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withRepresentedTypeOf(KClass, null) returns declarations with one of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = null
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withRepresentedTypeOf(SampleClass1::class, null)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withRepresentedTypeOf(null, KClass) returns declarations with one of given types`() {
        // given
        val type1 = null
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns true
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withRepresentedTypeOf(null, SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutRepresentedTypeOf(KClass) returns declaration without given type`() {
        // given
        val type = "com.lemonappdev.konsist.testdata.SampleClass1"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns true
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedTypeOf(SampleClass1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutRepresentedTypeOf(KClass) returns declaration without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = "com.lemonappdev.konsist.testdata.SampleClass2"
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns true
            }
        val declaration3: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutRepresentedTypeOf(SampleClass1::class, SampleClass2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutRepresentedTypeOf(KClass, null) returns declaration without any of given types`() {
        // given
        val type1 = "com.lemonappdev.konsist.testdata.SampleClass1"
        val type2 = null
        val declaration1: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns true
                every { representsType(type2) } returns false
            }
        val declaration2: KoRepresentsTypeProvider =
            mockk {
                every { representsType(type1) } returns false
                every { representsType(type2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutRepresentedTypeOf(SampleClass1::class, null)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
