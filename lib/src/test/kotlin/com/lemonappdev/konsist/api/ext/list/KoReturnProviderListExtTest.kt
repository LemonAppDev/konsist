package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoReturnProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoReturnProviderListExtTest {
    @Test
    fun `returnTypes returns return types from all declarations`() {
        // given
        val returnType1: KoTypeDeclaration = mockk()
        val returnType2: KoTypeDeclaration = mockk()
        val declaration1: KoReturnProvider =
            mockk {
                every { returnType } returns returnType1
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { returnType } returns returnType2
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { returnType } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.returnTypes

        // then
        sut shouldBeEqualTo listOf(returnType1, returnType2)
    }

    @Test
    fun `withReturnValue() returns declaration with return value other than Unit type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnValue } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnValue } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnValue()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutReturnValue() returns declaration with Unit return value`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnValue } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnValue } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnValue()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withReturnType() returns declaration with any return type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnType()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReturnType{} returns declaration which satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val type2: KoTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoReturnProvider =
            mockk {
                every { returnType } returns type1
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { returnType } returns type2
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { returnType } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReturnType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReturnType() returns declarations with one of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoReturnProvider =
            mockk {
                every { returnType?.name } returns typeName1
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { returnType?.name } returns typeName2
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { returnType?.name } returns typeName3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReturnType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReturnType() returns declaration without any return type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnType()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReturnType{} returns declarations which not satisfy predicate`() {
        // given
        val name1 = "name1"
        val name2 = "name2"
        val type1: KoTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val type2: KoTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoReturnProvider =
            mockk {
                every { returnType } returns type1
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { returnType } returns type2
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { returnType } returns null
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReturnType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2, declaration3)
    }

    @Test
    fun `withoutReturnType(name) returns declaration without any of given return types`() {
        // given
        val typeName1 = "SampleType1"
        val typeName2 = "SampleType2"
        val typeName3 = "SampleType3"
        val declaration1: KoReturnProvider =
            mockk {
                every { returnType?.name } returns typeName1
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { returnType?.name } returns typeName2
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { returnType?.name } returns typeName3
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReturnType(typeName1, typeName2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withReturnTypeOf(empty list) returns declaration with any return type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReturnTypeOf(empty set) returns declaration with any return type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutReturnTypeOf(empty list) returns declaration without return type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReturnTypeOf(empty set) returns declaration without return type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnType() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withReturnTypeOf(KClass) returns declarations with given return types`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withReturnTypeOf(KClass) returns declarations with one of given return types`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withReturnTypeOf(list of KClass) returns declarations with one of given return types`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses= listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withReturnTypeOf(set of KClass) returns declarations with one of given return types`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses= setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReturnTypeOf(KClass) returns declaration without given return type`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnTypeOf(SampleType1::class)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutReturnTypeOf(KClass) returns declaration without any of given return types`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutReturnTypeOf(SampleType1::class, SampleType2::class)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutReturnTypeOf(list of KClass) returns declaration without any of given return types`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses= listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutReturnTypeOf(set of KClass) returns declaration without any of given return types`() {
        // given
        val declaration1: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoReturnProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses= setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
