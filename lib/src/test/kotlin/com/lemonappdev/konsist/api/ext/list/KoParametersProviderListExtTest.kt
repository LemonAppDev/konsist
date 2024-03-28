package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoParametersProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParametersProviderListExtTest {
    @Test
    fun `parameters returns parameters from all declarations`() {
        // given
        val parameter1: KoParameterDeclaration = mockk()
        val parameter2: KoParameterDeclaration = mockk()
        val parameter3: KoParameterDeclaration = mockk()
        val declaration1: KoParametersProvider =
            mockk {
                every { parameters } returns listOf(parameter1, parameter2)
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { parameters } returns listOf(parameter3)
            }
        val declaration3: KoParametersProvider =
            mockk {
                every { parameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.parameters

        // then
        sut shouldBeEqualTo listOf(parameter1, parameter2, parameter3)
    }

    @Test
    fun `withParameters() returns declaration with any parameter`() {
        // given
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameters() } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameters()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterNamed(empty list) returns declaration with any parameter`() {
        // given
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameters() } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterNamed(empty set) returns declaration with any parameter`() {
        // given
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameters() } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParameters() returns declaration with no parameter`() {
        // given
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameters() } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameters()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterNamed(empty list) returns declaration with no parameter`() {
        // given
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameters() } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterNamed(empty set) returns declaration with no parameter`() {
        // given
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameters() } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParameterNamed(name) returns declaration with given parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterNamed(String) returns declaration with any of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameterNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterNamed(list of String) returns declaration with any of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withParameterNamed(set of String) returns declaration with any of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParameterNamed(name) returns declaration without given parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterNamed(String) returns declaration without any of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameterNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterNamed(list of String) returns declaration without any of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutParameterNamed(set of String) returns declaration without any of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParametersNamed(name) returns declaration with given parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParametersNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParametersNamed(String) returns declaration with all given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParametersNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParametersNamed(list of String) returns declaration with all given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllParametersNamed(set of String) returns declaration with all given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParametersNamed(name) returns declaration without given parameter`() {
        // given
        val name = "SampleName"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParametersNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParametersNamed(String) returns declaration without all of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParametersNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParametersNamed(list of String) returns declaration without all of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllParametersNamed(set of String) returns declaration without all of given parameters`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParametersWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParameter{} returns declaration with parameter which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameter(predicate) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameter(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withParameter(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutParameter{} returns declaration without parameter which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameter(predicate) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameter(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutParameter(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParameters{} returns declaration with all parameters satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParametersProvider =
            mockk {
                every { hasAllParameters(predicate) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasAllParameters(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParameters{} returns declaration with all parameters which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoParametersProvider =
            mockk {
                every { hasAllParameters(predicate) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasAllParameters(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withParameters{} returns declaration with parameters which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParameterDeclaration>) -> Boolean =
            { it.all { parameter -> parameter.hasNameEndingWith(suffix) } }
        val parameter1: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parameter2: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoParametersProvider =
            mockk {
                every { parameters } returns listOf(parameter1)
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { parameters } returns listOf(parameter2)
            }
        val declaration3: KoParametersProvider =
            mockk {
                every { parameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutParameters{} returns declaration without parameters which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoParameterDeclaration>) -> Boolean =
            { it.all { parameter -> parameter.hasNameEndingWith(suffix) } }
        val parameter1: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val parameter2: KoParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoParametersProvider =
            mockk {
                every { parameters } returns listOf(parameter1)
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { parameters } returns listOf(parameter2)
            }
        val declaration3: KoParametersProvider =
            mockk {
                every { parameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllParameters(String) returns declaration with all of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns true
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration3: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withAllParameters(parameter1, parameter2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllParameters(String) returns declaration without any parameter`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns true
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration3: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutAllParameters(parameter1, parameter2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withSomeParameters(String) returns declaration with given parameter`() {
        // given
        val parameter = "SampleParameter"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeParameters(parameter)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeParameters(String) returns declarations with at least one of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns true
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration3: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeParameters(parameter1, parameter2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeParameters(String) returns declaration without given parameter`() {
        // given
        val parameter = "SampleParameter"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeParameters(parameter)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeParameters(String) returns declarations without at least one of given parameters`() {
        // given
        val parameter1 = "SampleParameter1"
        val parameter2 = "SampleParameter2"
        val declaration1: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns true
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration2: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns true
            }
        val declaration3: KoParametersProvider =
            mockk {
                every { hasParameterNamed(parameter1) } returns false
                every { hasParameterNamed(parameter2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeParameters(parameter1, parameter2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
