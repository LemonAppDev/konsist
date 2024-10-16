package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoTypeParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoTypeParameterProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeParameterProviderListExtTest {
    @Test
    fun `typeParameters returns type parameters from all declarations`() {
        // given
        val typeParameter1: KoTypeParameterDeclaration = mockk()
        val typeParameter2: KoTypeParameterDeclaration = mockk()
        val typeParameter3: KoTypeParameterDeclaration = mockk()
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns listOf(typeParameter1, typeParameter2)
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns listOf(typeParameter3)
            }
        val declaration3: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.typeParameters

        // then
        sut shouldBeEqualTo listOf(typeParameter1, typeParameter2, typeParameter3)
    }

    @Test
    fun `withTypeParameters() returns declaration with any type parameter`() {
        // given
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeParameters()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeParameterNamed(empty list) returns declaration with any type parameter`() {
        // given
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeParameterNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeParameterNamed(empty set) returns declaration with any type parameter`() {
        // given
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeParameterNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeParameters() returns declaration with no type parameter`() {
        // given
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeParameters()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeParameterNamed(empty list) returns declaration with no type parameter`() {
        // given
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeParameterNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeParameterNamed(empty set) returns declaration with no type parameter`() {
        // given
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeParameterNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeParameterNamed(name) returns declaration with given type parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeParameterNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeParameterNamed(String) returns declaration with any of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeParameterNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeParameterNamed(list of String) returns declaration with any of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withTypeParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withTypeParameterNamed(set of String) returns declaration with any of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withTypeParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeParameterNamed(name) returns declaration without given type parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeParameterNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeParameterNamed(String) returns declaration without any of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeParameterNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeParameterNamed(list of String) returns declaration without any of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutTypeParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutTypeParameterNamed(set of String) returns declaration without any of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameterWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutTypeParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTypeParametersNamed(name) returns declaration with given type parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeParametersNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeParametersNamed(String) returns declaration with all given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeParametersNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeParametersNamed(list of String) returns declaration with all given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllTypeParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllTypeParametersNamed(set of String) returns declaration with all given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllTypeParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTypeParametersNamed(name) returns declaration without given type parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeParametersNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeParametersNamed(String) returns declaration without all of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeParametersNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeParametersNamed(list of String) returns declaration without all of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllTypeParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllTypeParametersNamed(set of String) returns declaration without all of given type parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParametersWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllTypeParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeParameter{} returns declaration with type parameter which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameter(predicate) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameter(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTypeParameter(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTypeParameter{} returns declaration without type parameter which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameter(predicate) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasTypeParameter(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTypeParameter(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllTypeParameters{} returns declaration with all type parameters satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasAllTypeParameters(predicate) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasAllTypeParameters(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllTypeParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllTypeParameters{} returns declaration with all type parameters which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoTypeParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { hasAllTypeParameters(predicate) } returns true
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { hasAllTypeParameters(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllTypeParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTypeParameters{} returns declaration with type parameters which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeParameterDeclaration>) -> Boolean =
            { it.all { typeParameter -> typeParameter.hasNameEndingWith(suffix) } }
        val typeParameter1: KoTypeParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeParameter2: KoTypeParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns listOf(typeParameter1)
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns listOf(typeParameter2)
            }
        val declaration3: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withTypeParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutTypeParameters{} returns declaration without type parameters which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoTypeParameterDeclaration>) -> Boolean =
            { it.all { typeParameter -> typeParameter.hasNameEndingWith(suffix) } }
        val typeParameter1: KoTypeParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val typeParameter2: KoTypeParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns listOf(typeParameter1)
            }
        val declaration2: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns listOf(typeParameter2)
            }
        val declaration3: KoTypeParameterProvider =
            mockk {
                every { typeParameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutTypeParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
