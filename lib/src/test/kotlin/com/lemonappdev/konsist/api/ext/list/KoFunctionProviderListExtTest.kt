package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoFunctionProvider
import com.lemonappdev.konsist.core.declaration.KoFunctionDeclarationCore
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionProviderListExtTest {
    @Test
    fun `functions() returns functions from all declarations`() {
        // given
        val function1: KoFunctionDeclarationCore = mockk()
        val function2: KoFunctionDeclarationCore = mockk()
        val function3: KoFunctionDeclarationCore = mockk()
        val declaration1: KoFunctionProvider =
            mockk {
                every { functions(includeNested = true, includeLocal = false) } returns
                    listOf(
                        function1,
                        function2,
                    )
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { functions(includeNested = true, includeLocal = false) } returns listOf(function3)
            }
        val declaration3: KoFunctionProvider =
            mockk {
                every { functions(includeNested = true, includeLocal = false) } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.functions(includeNested = true, includeLocal = false)

        // then
        sut shouldBeEqualTo listOf(function1, function2, function3)
    }

    @Test
    fun `withFunctions() returns declaration with any function`() {
        // given
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctions() } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFunctions()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFunctions() returns declaration without any function`() {
        // given
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctions() } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFunctions()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withFunctionNamed(name) returns declaration with given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFunctionNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withFunctionNamed(String) returns declaration with any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name1, name2) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFunctionNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFunctionNamed(name) returns declaration without given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFunctionNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutFunctionNamed(String) returns declaration without any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name1, name2) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionWithName(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFunctionNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllFunctionsNamed(name) returns declaration with given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllFunctionsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllFunctionsNamed(String) returns declaration with all given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllFunctionsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllFunctionsNamed(name) returns declaration without given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllFunctionsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllFunctionsNamed(String) returns declaration without all of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name1, name2) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunctionsWithAllNames(name1, name2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllFunctionsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withFunction{} returns declaration with functions which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunction(true, true, predicate) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunction(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFunction(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFunction{} returns declaration without functions which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasFunction(true, true, predicate) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasFunction(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFunction(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllFunctions{} returns declaration with all functions satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasAllFunctions(true, true, predicate) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasAllFunctions(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllFunctions(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllFunctions{} returns declaration with all functions which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoFunctionProvider =
            mockk {
                every { hasAllFunctions(true, true, predicate) } returns true
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { hasAllFunctions(true, true, predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllFunctions(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withFunctions{} returns declaration with functions which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoFunctionDeclaration>) -> Boolean =
            { it.all { koFunction -> koFunction.hasNameEndingWith(suffix) } }
        val function1: KoFunctionDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val function2: KoFunctionDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoFunctionProvider =
            mockk {
                every { functions() } returns listOf(function1)
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { functions() } returns listOf(function2)
            }
        val declaration3: KoFunctionProvider =
            mockk {
                every { functions() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withFunctions(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutFunctions{} returns declaration without functions which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoFunctionDeclaration>) -> Boolean =
            { it.all { koFunction -> koFunction.hasNameEndingWith(suffix) } }
        val function1: KoFunctionDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val function2: KoFunctionDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoFunctionProvider =
            mockk {
                every { functions() } returns listOf(function1)
            }
        val declaration2: KoFunctionProvider =
            mockk {
                every { functions() } returns listOf(function2)
            }
        val declaration3: KoFunctionProvider =
            mockk {
                every { functions() } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutFunctions(true, true, predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
