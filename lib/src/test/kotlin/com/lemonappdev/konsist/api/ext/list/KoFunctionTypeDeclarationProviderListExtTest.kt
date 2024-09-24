package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionTypeDeclarationProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionTypeDeclarationProviderListExtTest {
    @Test
    fun `returnTypes returns return types from all declarations`() {
        // given
        val returnType1: KoTypeDeclaration = mockk()
        val returnType2: KoTypeDeclaration = mockk()
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { returnType } returns returnType1
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { returnType } returns returnType2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.returnTypes

        // then
        sut shouldBeEqualTo listOf(returnType1, returnType2)
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
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { returnType } returns type1
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { returnType } returns type2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration1)
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
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { returnType } returns type1
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { returnType } returns type2
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnType { it.name == name1 }

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withReturnTypeOf(empty list) returns declaration with any return type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnTypeOf(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withReturnTypeOf(empty set) returns declaration with any return type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withReturnTypeOf(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReturnTypeOf(empty list) returns declaration without any return type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnTypeOf(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutReturnTypeOf(empty set) returns declaration without any return type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutReturnTypeOf(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withReturnTypeOf(KClass) returns declaration with one of given return type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
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
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
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
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withReturnTypeOf(set of KClass) returns declarations with one of given return types`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutReturnTypeOf(KClass) returns declaration without one of given return type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
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
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
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
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = listOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withoutReturnTypeOf(set of KClass) returns declaration without any of given return types`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns true
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns true
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasReturnTypeOf(SampleType1::class) } returns false
                every { hasReturnTypeOf(SampleType2::class) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)
        val kClasses = setOf(SampleType1::class, SampleType2::class)

        // when
        val sut = declarations.withoutReturnTypeOf(kClasses)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `parameterTypes returns parameter types from all declarations`() {
        // given
        val parameterType1: KoParameterDeclaration = mockk()
        val parameterType2: KoParameterDeclaration = mockk()
        val parameterType3: KoParameterDeclaration = mockk()
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns listOf(parameterType1, parameterType2)
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns listOf(parameterType3)
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parameterTypes

        // then
        sut shouldBeEqualTo listOf(parameterType1, parameterType2, parameterType3)
    }

    @Test
    fun `withParameterTypeNamed(empty list) returns declaration with any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterTypeNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withParameterTypeNamed(empty set) returns declaration with any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterTypeNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllParameterTypesNamed(empty list) returns declaration with any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParameterTypesNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllParameterTypesNamed(empty set) returns declaration with any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParameterTypesNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutParameterTypeNamed(empty list) returns declaration without any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterTypeNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutParameterTypeNamed(empty set) returns declaration without any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterTypeNamed(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllParameterTypesNamed(empty list) returns declaration without any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParameterTypesNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllParameterTypesNamed(empty set) returns declaration without any parameter type`() {
        // given
        val declaration1: KoFunctionTypeDeclarationProvider = mockk()
        val declaration2: KoFunctionTypeDeclarationProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParameterTypesNamed(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withParameterTypeNamed(name) returns declaration with given parameter type`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterTypeNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterTypeNamed(String) returns declaration with any of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterTypeNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterTypeNamed(list of String) returns declaration with any of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withParameterTypeNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterTypeNamed(set of String) returns declaration with any of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withParameterTypeNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParameterTypeNamed(name) returns declaration without given parameter type`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterTypeNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterTypeNamed(String) returns declaration without any of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterTypeNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterTypeNamed(list of String) returns declaration without any of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutParameterTypeNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterTypeNamed(set of String) returns declaration without any of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypeWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutParameterTypeNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParameterTypesNamed(name) returns declaration with given parameter type`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParameterTypesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParameterTypesNamed(String) returns declaration with all given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParameterTypesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParameterTypesNamed(list of String) returns declaration with all given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllParameterTypesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParameterTypesNamed(set of String) returns declaration with all given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllParameterTypesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParameterTypesNamed(name) returns declaration without given parameter type`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParameterTypesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParameterTypesNamed(String) returns declaration without all of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParameterTypesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParameterTypesNamed(list of String) returns declaration without all of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllParameterTypesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParameterTypesNamed(set of String) returns declaration without all of given parameter types`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterTypesWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllParameterTypesNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParameterType{} returns declaration with parameter type which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterType(predicate) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterType(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterType(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParameterType{} returns declaration without parameter type which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterType(predicate) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasParameterType(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterType(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParameterTypes{} returns declaration with all parameter types satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasAllParameterTypes(predicate) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasAllParameterTypes(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParameterTypes(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParameterTypes{} returns declaration with all parameter types which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasAllParameterTypes(predicate) } returns true
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { hasAllParameterTypes(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParameterTypes(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParameterType{} returns declaration with parameter types which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParameterDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val parameterType1: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parameterType2: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns listOf(parameterType1)
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns listOf(parameterType2)
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParameterTypes(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutParameterType{} returns declaration without parameter types which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParameterDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val parameterType1: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parameterType2: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns listOf(parameterType1)
            }
        val declaration2: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns listOf(parameterType2)
            }
        val declaration3: KoFunctionTypeDeclarationProvider =
            mockk {
                every { parameterTypes } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParameterTypes(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
