package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoContextParameterDeclaration
import com.lemonappdev.konsist.api.provider.KoContextParametersProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoContextParametersProviderListExtTest {
    @Test
    fun `contextParameters returns context parameters from all declarations`() {
        // given
        val contextParameter1: KoContextParameterDeclaration = mockk()
        val contextParameter2: KoContextParameterDeclaration = mockk()
        val contextParameter3: KoContextParameterDeclaration = mockk()
        val declaration1: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns listOf(contextParameter1, contextParameter2)
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns listOf(contextParameter3)
            }
        val declaration3: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.contextParameters

        // then
        sut shouldBeEqualTo listOf(contextParameter1, contextParameter2, contextParameter3)
    }

    @Test
    fun `withContextParameters() returns declaration with any context parameter`() {
        // given
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withContextParameters()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withContextParameterNamed(empty list) returns declaration with any context parameter`() {
        // given
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withContextParameterNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withContextParameterNamed(empty set) returns declaration with any context parameter`() {
        // given
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withContextParameterNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutContextParameters() returns declaration with no context parameter`() {
        // given
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutContextParameters()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutContextParameterNamed(empty list) returns declaration with no context parameter`() {
        // given
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutContextParameterNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutContextParameterNamed(empty set) returns declaration with no context parameter`() {
        // given
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameters() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutContextParameterNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withContextParameterNamed(name) returns declaration with given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withContextParameterNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withContextParameterNamed(String) returns declaration with any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withContextParameterNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withContextParameterNamed(list of String) returns declaration with any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withContextParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withContextParameterNamed(set of String) returns declaration with any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withContextParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withContextParameterNamed(name) with ignore case returns declaration with given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withContextParameterNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withContextParameterNamed(list of String) with ignore case returns declaration with any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withContextParameterNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutContextParameterNamed(name) returns declaration without given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutContextParameterNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutContextParameterNamed(String) returns declaration without any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutContextParameterNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutContextParameterNamed(list of String) returns declaration without any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutContextParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutContextParameterNamed(set of String) returns declaration without any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutContextParameterNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutContextParameterNamed(name) with ignore case returns declaration without given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutContextParameterNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutContextParameterNamed(list of String) with ignore case returns declaration without any of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameterWithName(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutContextParameterNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllContextParametersNamed(name) returns declaration with given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllContextParametersNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllContextParametersNamed(String) returns declaration with all given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllContextParametersNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllContextParametersNamed(list of String) returns declaration with all given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllContextParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllContextParametersNamed(set of String) returns declaration with all given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllContextParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllContextParametersNamed(name) with ignore case returns declaration with given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllContextParametersNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllContextParametersNamed(list of String) with ignore case returns declaration with all given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllContextParametersNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllContextParametersNamed(name) returns declaration without given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllContextParametersNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllContextParametersNamed(String) returns declaration without all of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllContextParametersNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllContextParametersNamed(list of String) returns declaration without all of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllContextParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllContextParametersNamed(set of String) returns declaration without all of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllContextParametersNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllContextParametersNamed(name) with ignore case returns declaration without given context parameter`() {
        // given
        val name = "FixtureName"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllContextParametersNamed(name, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllContextParametersNamed(list of String) with ignore case returns declaration without all of given context parameters`() {
        // given
        val name1 = "FixtureName1"
        val name2 = "FixtureName2"
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2), ignoreCase = true) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParametersWithAllNames(listOf(name1, name2), ignoreCase = true) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllContextParametersNamed(names, ignoreCase = true)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withContextParameter{} returns declaration with context parameter which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoContextParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameter(predicate) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameter(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withContextParameter(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutContextParameter{} returns declaration without context parameter which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoContextParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasContextParameter(predicate) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasContextParameter(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutContextParameter(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllContextParameters{} returns declaration with all context parameters satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoContextParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasAllContextParameters(predicate) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasAllContextParameters(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllContextParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllContextParameters{} returns declaration with all context parameters which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoContextParameterDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoContextParametersProvider =
            mockk {
                every { hasAllContextParameters(predicate) } returns true
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { hasAllContextParameters(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllContextParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withContextParameters{} returns declaration with context parameters which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoContextParameterDeclaration>) -> Boolean =
            { it.all { contextParameter -> contextParameter.hasNameEndingWith(suffix) } }
        val contextParameter1: KoContextParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val contextParameter2: KoContextParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns listOf(contextParameter1)
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns listOf(contextParameter2)
            }
        val declaration3: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withContextParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutContextParameters{} returns declaration without context parameters which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoContextParameterDeclaration>) -> Boolean =
            { it.all { contextParameter -> contextParameter.hasNameEndingWith(suffix) } }
        val contextParameter1: KoContextParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val contextParameter2: KoContextParameterDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns listOf(contextParameter1)
            }
        val declaration2: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns listOf(contextParameter2)
            }
        val declaration3: KoContextParametersProvider =
            mockk {
                every { contextParameters } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutContextParameters(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
