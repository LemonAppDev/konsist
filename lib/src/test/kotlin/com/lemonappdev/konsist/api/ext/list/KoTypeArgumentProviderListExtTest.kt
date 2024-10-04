package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.type.KoBaseTypeDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeArgumentProvider
import com.lemonappdev.konsist.testdata.SampleType1
import com.lemonappdev.konsist.testdata.SampleType2
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeArgumentProviderListExtTest {
    @Test
    fun `typeArguments returns type arguments from all declarations`() {
        // given
        val typeArgument1: KoTypeArgumentDeclaration = mockk()
        val typeArgument2: KoTypeArgumentDeclaration = mockk()
        val typeArgument3: KoTypeArgumentDeclaration = mockk()
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument1, typeArgument2)
            }
        val declaration2: KoTypeArgumentProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument3)
            }
        val declaration3: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withTypeArgumentNamed(empty set) returns declaration with any type argument`() {
        // given
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeArgumentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllTypeArgumentsNamed(empty list) returns declaration with any type argument`() {
        // given
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withAllTypeArgumentsNamed(empty set) returns declaration with any type argument`() {
        // given
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeArgumentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutTypeArgumentNamed(empty list) returns declaration without any type argument`() {
        // given
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutTypeArgumentNamed(empty set) returns declaration without any type argument`() {
        // given
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeArgumentNamed(emptySet())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(empty list) returns declaration without any type argument`() {
        // given
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeArgumentsNamed(emptyList())

        // then
        sut shouldBeEqualTo emptyList()
    }

    @Test
    fun `withoutAllTypeArgumentsNamed(empty set) returns declaration without any type argument`() {
        // given
        val declaration1: KoTypeArgumentProvider = mockk()
        val declaration2: KoTypeArgumentProvider = mockk()
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgumentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val predicate: (KoTypeArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgument(predicate) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val predicate: (KoTypeArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasTypeArgument(predicate) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val predicate: (KoTypeArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasAllTypeArguments(predicate) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val predicate: (KoTypeArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { hasAllTypeArguments(predicate) } returns true
            }
        val declaration2: KoTypeArgumentProvider =
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
        val predicate: (List<KoTypeArgumentDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val typeArgument1: KoTypeArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeArgument2: KoTypeArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument1)
            }
        val declaration2: KoTypeArgumentProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument2)
            }
        val declaration3: KoTypeArgumentProvider =
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
        val predicate: (List<KoTypeArgumentDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val typeArgument1: KoTypeArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeArgument2: KoTypeArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoTypeArgumentProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument1)
            }
        val declaration2: KoTypeArgumentProvider =
            mockk {
                every { typeArguments } returns listOf(typeArgument2)
            }
        val declaration3: KoTypeArgumentProvider =
            mockk {
                every { typeArguments } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
