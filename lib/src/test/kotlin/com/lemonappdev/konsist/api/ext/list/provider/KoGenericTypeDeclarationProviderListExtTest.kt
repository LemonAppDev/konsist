package com.lemonappdev.konsist.api.ext.list.provider

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGenericTypeDeclarationProviderListExtTest {
    @Test
    fun `types returns generic types from all declarations`() {
        // given
        val type1: KoBaseTypeDeclaration = mockk()
        val type2: KoBaseTypeDeclaration = mockk()
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { type } returns type1
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
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
        val type1: KoBaseTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val type2: KoBaseTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { type } returns type1
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
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
        val type1: KoBaseTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val type2: KoBaseTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { type } returns type1
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { type } returns type2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeOf(empty list) returns declaration with any generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTypeOf(empty set) returns declaration with any generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTypeOf(empty list) returns declaration without any generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutTypeOf(empty set) returns declaration without any generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withTypeOf(KClass) returns declaration with one of given generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeOf(KClass) returns declarations with one of given generic types`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
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
    fun `withTypeOf(list of KClass) returns declarations with one of given generic types`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTypeOf(set of KClass) returns declarations with one of given generic types`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns declaration without one of given generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeOf(KClass) returns declaration without any of given generic types`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTypeOf(list of KClass) returns declaration without any of given generic types`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutTypeOf(set of KClass) returns declaration without any of given generic types`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns true
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeOf(SampleType1::class) } returns false
                every { hasTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
