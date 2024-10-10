package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGenericTypeProviderListExtTest {
    @Test
    fun `genericType returns generic types from all declarations`() {
        // given
        val type1: KoBaseTypeDeclaration = mockk()
        val type2: KoBaseTypeDeclaration = mockk()
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { genericType } returns type1
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { genericType } returns type2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.genericTypes

        // then
        sut shouldBeEqualTo listOf(type1, type2)
    }

    @Test
    fun `withGenericType{} returns declaration which satisfy predicate`() {
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
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { genericType } returns type1
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { genericType } returns type2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGenericType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutGenericType{} returns declarations which not satisfy predicate`() {
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
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { genericType } returns type1
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { genericType } returns type2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGenericType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withGenericTypeOf(empty list) returns declaration with any generic type`() {
        // given
        val declaration1: KoGenericTypeProvider = mockk()
        val declaration2: KoGenericTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGenericTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withGenericTypeOf(empty set) returns declaration with any generic type`() {
        // given
        val declaration1: KoGenericTypeProvider = mockk()
        val declaration2: KoGenericTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGenericTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutGenericTypeOf(empty list) returns declaration without any generic type`() {
        // given
        val declaration1: KoGenericTypeProvider = mockk()
        val declaration2: KoGenericTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGenericTypeOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutGenericTypeOf(empty set) returns declaration without any generic type`() {
        // given
        val declaration1: KoGenericTypeProvider = mockk()
        val declaration2: KoGenericTypeProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGenericTypeOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withGenericTypeOf(KClass) returns declaration with one of given generic type`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGenericTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withGenericTypeOf(KClass) returns declarations with one of given generic types`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withGenericTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withGenericTypeOf(list of KClass) returns declarations with one of given generic types`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withGenericTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withGenericTypeOf(set of KClass) returns declarations with one of given generic types`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withGenericTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutGenericTypeOf(KClass) returns declaration without one of given generic type`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGenericTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutGenericTypeOf(KClass) returns declaration without any of given generic types`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutGenericTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutGenericTypeOf(list of KClass) returns declaration without any of given generic types`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutGenericTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutGenericTypeOf(set of KClass) returns declaration without any of given generic types`() {
        // given
        val declaration1: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutGenericTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
