package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoFunctionDeclaration
import com.lemonappdev.konsist.api.provider.KoLocalFunctionProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoLocalFunctionProviderListExtTest {
    @Test
    fun `localFunctions returns local declarations from all declarations`() {
        // given
        val localFunction1: KoFunctionDeclaration = mockk()
        val localFunction2: KoFunctionDeclaration = mockk()
        val localFunction3: KoFunctionDeclaration = mockk()
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns listOf(localFunction1, localFunction2)
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns listOf(localFunction3)
            }
        val declaration3: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.localFunctions

        // then
        sut shouldBeEqualTo listOf(localFunction1, localFunction2, localFunction3)
    }

    @Test
    fun `withLocalFunctions() returns declaration with any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalFunctions()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalFunctionNamed(empty list) returns declaration with any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalFunctionNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalFunctionNamed(empty set) returns declaration with any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalFunctionNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalFunctionsNamed(empty list) returns declaration with any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalFunctionsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalFunctionsNamed(empty set) returns declaration with any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalFunctionsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalFunctions() returns declaration without any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalFunctions()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalFunctionNamed(empty list) returns declaration without any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalFunctionNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalFunctionNamed(empty set) returns declaration without any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalFunctionNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalFunctionsNamed(empty list) returns declaration without any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalFunctionsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalFunctionsNamed(empty set) returns declaration without any function`() {
        // given
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctions() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalFunctionsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalFunctionNamed(name) returns declaration with given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalFunctionNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalFunctionNamed(String) returns declaration with any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalFunctionNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalFunctionNamed(list of String) returns declaration with any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withLocalFunctionNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withLocalFunctionNamed(set of String) returns declaration with any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withLocalFunctionNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalFunctionNamed(name) returns declaration without given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalFunctionNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalFunctionNamed(String) returns declaration without any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalFunctionNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalFunctionNamed(list of String) returns declaration without any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutLocalFunctionNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutLocalFunctionNamed(set of String) returns declaration without any of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutLocalFunctionNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllLocalFunctionsNamed(name) returns declaration with given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalFunctionsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalFunctionsNamed(String) returns declaration with all given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalFunctionsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalFunctionsNamed(list of String) returns declaration with all given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllLocalFunctionsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllLocalFunctionsNamed(set of String) returns declaration with all given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllLocalFunctionsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllLocalFunctionsNamed(name) returns declaration without given function`() {
        // given
        val name = "SampleName"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalFunctionsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalFunctionsNamed(String) returns declaration without all of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalFunctionsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalFunctionsNamed(list of String) returns declaration without all of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllLocalFunctionsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllLocalFunctionsNamed(set of String) returns declaration without all of given functions`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunctionsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllLocalFunctionsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalFunction{} returns declaration with local functions which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunction(predicate) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunction(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLocalFunction(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLocalFunction{} returns declaration without local functions which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunction(predicate) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasLocalFunction(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLocalFunction(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllLocalFunctions{} returns declaration with all local functions satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasAllLocalFunctions(predicate) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasAllLocalFunctions(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllLocalFunctions(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllLocalFunctions{} returns declaration with all local functions which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoFunctionDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { hasAllLocalFunctions(predicate) } returns true
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { hasAllLocalFunctions(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllLocalFunctions(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLocalFunction{} returns declaration with functions which satisfy predicate`() {
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
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns listOf(function1)
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns listOf(function2)
            }
        val declaration3: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withLocalFunctions(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutLocalFunction{} returns declaration without functions which satisfy predicate`() {
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
        val declaration1: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns listOf(function1)
            }
        val declaration2: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns listOf(function2)
            }
        val declaration3: KoLocalFunctionProvider =
            mockk {
                every { localFunctions } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutLocalFunctions(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
