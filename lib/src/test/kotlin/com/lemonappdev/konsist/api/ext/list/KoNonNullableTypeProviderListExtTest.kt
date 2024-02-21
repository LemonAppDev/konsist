package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoNonNullableTypeProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNonNullableTypeProviderListExtTest {
    @Test
    fun `types returns types from all declarations`() {
        // given
        val type1: KoTypeDeclaration = mockk()
        val type2: KoTypeDeclaration = mockk()
        val declaration1: KoNonNullableTypeProvider = mockk {
            every { type } returns type1
        }
        val declaration2: KoNonNullableTypeProvider = mockk {
            every { type } returns type2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.types

        // then
        sut shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withType{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoTypeDeclaration = mockk {
            every { name } returns name1
        }
        val type2: KoTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoNonNullableTypeProvider = mockk {
            every { type } returns type1
        }
        val declaration2: KoNonNullableTypeProvider = mockk {
            every { type } returns type2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutType{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoTypeDeclaration = mockk {
            every { name } returns name1
        }
        val type2: KoTypeDeclaration = mockk {
            every { name } returns name2
        }
        val declaration1: KoNonNullableTypeProvider = mockk {
            every { type } returns type1
        }
        val declaration2: KoNonNullableTypeProvider = mockk {
            every { type } returns type2
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeOf(KClass) returns declaration with given return type`() {
        // given
        val declaration1: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns true
        }
        val declaration2: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeOf(KClass) returns declarations with one of given return types`() {
        // given
        val declaration1: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns true
            every { hasTypeOf(SampleType2::class) } returns false
        }
        val declaration2: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns false
            every { hasTypeOf(SampleType2::class) } returns true
        }
        val declaration3: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns false
            every { hasTypeOf(SampleType2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns declaration without given return type`() {
        // given
        val declaration1: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns true
        }
        val declaration2: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns declaration without any of given return types`() {
        // given
        val declaration1: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns true
            every { hasTypeOf(SampleType2::class) } returns false
        }
        val declaration2: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns false
            every { hasTypeOf(SampleType2::class) } returns true
        }
        val declaration3: KoNonNullableTypeProvider = mockk {
            every { hasTypeOf(SampleType1::class) } returns false
            every { hasTypeOf(SampleType2::class) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
