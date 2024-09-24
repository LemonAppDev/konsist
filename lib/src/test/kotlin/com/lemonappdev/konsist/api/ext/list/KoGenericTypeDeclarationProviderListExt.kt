package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoGenericTypeDeclarationProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGenericTypeDeclarationProviderListExt {
    @Test
    fun `genericTypes returns generic types from all declarations`() {
        // given
        val genericType1: KoTypeDeclaration = mockk()
        val genericType2: KoTypeDeclaration = mockk()
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { genericType } returns genericType1
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { genericType } returns genericType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.genericTypes

        // then
        sut shouldBeEqualTo listOf(genericType1, genericType2)
    }

    @Test
    fun `withGenericType{} returns declaration which satisfy predicate`() {
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { genericType } returns type1
            }
        val declaration2: KoGenericTypeDeclarationProvider =
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
        val type1: KoTypeDeclaration =
            mockk {
                every { name } returns name1
            }
        val type2: KoTypeDeclaration =
            mockk {
                every { name } returns name2
            }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { genericType } returns type1
            }
        val declaration2: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGenericTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withGenericTypeOf(empty set) returns declaration with any generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGenericTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutGenericTypeOf(empty list) returns declaration without any generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGenericTypeOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutGenericTypeOf(empty set) returns declaration without any generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGenericTypeOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withGenericTypeOf(KClass) returns declaration with one of given generic type`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
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
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns true
                every { hasGenericTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasGenericTypeOf(SampleType1::class) } returns false
                every { hasGenericTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoGenericTypeDeclarationProvider =
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

    @Test
    fun `typeArguments returns type arguments from all declarations`() {
        // given
        val typeArgument1: KoTypeDeclaration = mockk()
        val typeArgument2: KoTypeDeclaration = mockk()
        val typeArgument3: KoTypeDeclaration = mockk()
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument1, typeArgument2)
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument3)
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeArguments

        // then
        sut shouldBeEqualTo listOf(typeArgument1, typeArgument2, typeArgument3)
    }

    @Test
    fun `withTypeArgumentNamed(empty list) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTypeArgumentNamed(empty set) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllTypeArgumentsNamed(empty list) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllTypeArgumentsNamed(empty set) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTypeArgumentNamed(empty list) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutTypeArgumentNamed(empty set) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(empty list) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(empty set) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsNamed(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withTypeArgumentNamed(name) returns declaration with given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeArgumentNamed(String) returns declaration with any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeArgumentNamed(list of String) returns declaration with any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withTypeArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeArgumentNamed(set of String) returns declaration with any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withTypeArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeArgumentNamed(name) returns declaration without given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeArgumentNamed(String) returns declaration without any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeArgumentNamed(list of String) returns declaration without any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeArgumentNamed(set of String) returns declaration without any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTypeArgumentsNamed(name) returns declaration with given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeArgumentsNamed(String) returns declaration with all given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeArgumentsNamed(list of String) returns declaration with all given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeArgumentsNamed(set of String) returns declaration with all given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(name) returns declaration without given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(String) returns declaration without all of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(list of String) returns declaration without all of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllTypeArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(set of String) returns declaration without all of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllTypeArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeArgument{} returns declaration with type argument which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgument(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgument(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgument(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeArgument{} returns declaration without type argument which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgument(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgument(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgument(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTypeArguments{} returns declaration with all type arguments satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArguments(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArguments(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTypeArguments{} returns declaration with all type arguments which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArguments(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArguments(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeArgument{} returns declaration with type arguments which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val typeArgument1: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeArgument2: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument1)
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument2)
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTypeArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutTypeArgument{} returns declaration without type arguments which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val typeArgument1: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeArgument2: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument1)
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument2)
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArguments } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }













    @Test
    fun `typeArgumentsFlatten returns type arguments from all declarations`() {
        // given
        val typeArgument1: KoTypeDeclaration = mockk()
        val typeArgument2: KoTypeDeclaration = mockk()
        val typeArgument3: KoTypeDeclaration = mockk()
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns listOf(typeArgument1, typeArgument2)
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns listOf(typeArgument3)
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeArgumentsFlatten

        // then
        sut shouldBeEqualTo listOf(typeArgument1, typeArgument2, typeArgument3)
    }

    @Test
    fun `withTypeArgumentFlattenNamed(empty list) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentFlattenNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTypeArgumentFlattenNamed(empty set) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentFlattenNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllTypeArgumentsFlattenNamed(empty list) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsFlattenNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllTypeArgumentsFlattenNamed(empty set) returns declaration with any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsFlattenNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTypeArgumentFlattenNamed(empty list) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentFlattenNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutTypeArgumentFlattenNamed(empty set) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentFlattenNamed(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllTypeArgumentsFlattenNamed(empty list) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsFlattenNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllTypeArgumentsFlattenNamed(empty set) returns declaration without any type argument`() {
        // given
        val declaration1: KoGenericTypeDeclarationProvider = mockk()
        val declaration2: KoGenericTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsFlattenNamed(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withTypeArgumentFlattenNamed(name) returns declaration with given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentFlattenNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeArgumentFlattenNamed(String) returns declaration with any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentFlattenNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeArgumentFlattenNamed(list of String) returns declaration with any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withTypeArgumentFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeArgumentFlattenNamed(set of String) returns declaration with any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withTypeArgumentFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeArgumentFlattenNamed(name) returns declaration without given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentFlattenNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeArgumentFlattenNamed(String) returns declaration without any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentFlattenNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeArgumentFlattenNamed(list of String) returns declaration without any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutTypeArgumentFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeArgumentFlattenNamed(set of String) returns declaration without any of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlattenWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutTypeArgumentFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTypeArgumentsFlattenNamed(name) returns declaration with given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsFlattenNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeArgumentsFlattenNamed(String) returns declaration with all given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsFlattenNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeArgumentsFlattenNamed(list of String) returns declaration with all given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllTypeArgumentsFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeArgumentsFlattenNamed(set of String) returns declaration with all given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllTypeArgumentsFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTypeArgumentsFlattenNamed(name) returns declaration without given type argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsFlattenNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeArgumentsFlattenNamed(String) returns declaration without all of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsFlattenNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeArgumentsFlattenNamed(list of String) returns declaration without all of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllTypeArgumentsFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeArgumentsFlattenNamed(set of String) returns declaration without all of given type arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentsFlattenWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllTypeArgumentsFlattenNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeArgumentFlatten{} returns declaration with type argument which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlatten(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlatten(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentFlatten(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeArgumentFlatten{} returns declaration without type argument which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlatten(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasTypeArgumentFlatten(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentFlatten(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTypeArgumentsFlatten{} returns declaration with all type arguments satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArgumentsFlatten(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArgumentsFlatten(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsFlatten(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTypeArgumentsFlatten{} returns declaration with all type arguments which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArgumentsFlatten(predicate) } returns true
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { hasAllTypeArgumentsFlatten(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsFlatten(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeArgumentFlatten{} returns declaration with type arguments which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val typeArgument1: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeArgument2: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns listOf(typeArgument1)
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns listOf(typeArgument2)
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTypeArgumentsFlatten(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutTypeArgumentFlatten{} returns declaration without type arguments which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val typeArgument1: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeArgument2: KoTypeDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns listOf(typeArgument1)
            }
        val declaration2: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns listOf(typeArgument2)
            }
        val declaration3: KoGenericTypeDeclarationProvider =
            mockk {
                every { typeArgumentsFlatten } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeArgumentsFlatten(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
