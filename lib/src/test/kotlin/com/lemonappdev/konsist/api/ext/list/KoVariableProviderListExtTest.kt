package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoVariableDeclaration
import com.lemonappdev.konsist.api.provider.KoVariableProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableProviderListExtTest {
    @Test
    fun `variables returns variables from all declarations`() {
        // given
        val variable1: KoVariableDeclaration = mockk()
        val variable2: KoVariableDeclaration = mockk()
        val variable3: KoVariableDeclaration = mockk()
        val declaration1: KoVariableProvider =
            mockk {
                every { variables } returns listOf(variable1, variable2)
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { variables } returns listOf(variable3)
            }
        val declaration3: KoVariableProvider =
            mockk {
                every { variables } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.variables

        // then
        sut shouldBeEqualTo listOf(variable1, variable2, variable3)
    }

    @Test
    fun `withVariables() returns declaration with any variable`() {
        // given
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariables() } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariables() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVariables()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVariables() returns declaration without any variable`() {
        // given
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariables() } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariables() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVariables()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withVariableNamed(name) returns declaration with given variable`() {
        // given
        val name = "SampleName"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVariableNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withVariableNamed(String) returns declaration with any of given variables`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name1, name2) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVariableNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVariableNamed(name) returns declaration without given variable`() {
        // given
        val name = "SampleName"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVariableNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutVariableNamed(String) returns declaration without any of given variables`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name1, name2) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariableWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVariableNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllVariablesNamed(name) returns declaration with given variable`() {
        // given
        val name = "SampleName"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllVariablesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllVariablesNamed(String) returns declaration with all given variables`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllVariablesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllVariablesNamed(name) returns declaration without given variable`() {
        // given
        val name = "SampleName"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllVariablesNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllVariablesNamed(String) returns declaration without all of given variables`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariablesWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllVariablesNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withVariable{} returns declaration with any variable which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoVariableDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariable(predicate) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariable(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVariable(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVariable{} returns declaration without none variable which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoVariableDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoVariableProvider =
            mockk {
                every { hasVariable(predicate) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasVariable(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVariable(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllVariables{} returns declaration with all variables satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoVariableDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoVariableProvider =
            mockk {
                every { hasAllVariables(predicate) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasAllVariables(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllVariables(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllVariables{} returns declaration with all variables which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoVariableDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoVariableProvider =
            mockk {
                every { hasAllVariables(predicate) } returns true
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { hasAllVariables(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllVariables(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withVariable{} returns declaration with variables which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoVariableDeclaration>) -> Boolean =
            { it.all { koVariable -> koVariable.hasNameEndingWith(suffix) } }
        val variable1: KoVariableDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val variable2: KoVariableDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoVariableProvider =
            mockk {
                every { variables } returns listOf(variable1)
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { variables } returns listOf(variable2)
            }
        val declaration3: KoVariableProvider =
            mockk {
                every { variables } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withVariables(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutVariable{} returns declaration without variables which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoVariableDeclaration>) -> Boolean =
            { it.all { koVariable -> koVariable.hasNameEndingWith(suffix) } }
        val variable1: KoVariableDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val variable2: KoVariableDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoVariableProvider =
            mockk {
                every { variables } returns listOf(variable1)
            }
        val declaration2: KoVariableProvider =
            mockk {
                every { variables } returns listOf(variable2)
            }
        val declaration3: KoVariableProvider =
            mockk {
                every { variables } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutVariables(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
