package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentProviderListExtTest {
    @Test
    fun `arguments returns arguments from all declarations`() {
        // given
        val argument1: KoArgumentDeclaration = mockk()
        val argument2: KoArgumentDeclaration = mockk()
        val argument3: KoArgumentDeclaration = mockk()
        val declaration1: KoArgumentProvider =
            mockk {
                every { arguments } returns listOf(argument1, argument2)
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { arguments } returns listOf(argument3)
            }
        val declaration3: KoArgumentProvider =
            mockk {
                every { arguments } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.arguments

        // then
        sut shouldBeEqualTo listOf(argument1, argument2, argument3)
    }

    @Test
    fun `withArguments() returns declaration with any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArguments()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withArgumentNamed(empty list) returns declaration with any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArgumentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withArgumentNamed(empty set) returns declaration with any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArgumentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllArgumentsNamed(empty list) returns declaration with any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllArgumentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllArgumentsNamed(empty set) returns declaration with any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllArgumentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutArguments() returns declaration without any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArguments()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutArgumentNamed(empty list) returns declaration without any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArgumentNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutArgumentNamed(empty set) returns declaration without any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArgumentNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllArgumentsNamed(empty list) returns declaration without any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllArgumentsNamed(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllArgumentsNamed(empty set) returns declaration without any argument`() {
        // given
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArguments() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllArgumentsNamed(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withArgumentNamed(name) returns declaration with given argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArgumentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withArgumentNamed(String) returns declaration with any of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArgumentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withArgumentNamed(list of String) returns declaration with any of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withArgumentNamed(set of String) returns declaration with any of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutArgumentNamed(name) returns declaration without given argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArgumentNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutArgumentNamed(String) returns declaration without any of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArgumentNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutArgumentNamed(list of String) returns declaration without any of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutArgumentNamed(set of String) returns declaration without any of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(setOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentWithName(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutArgumentNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllArgumentsNamed(name) returns declaration with given argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllArgumentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllArgumentsNamed(String) returns declaration with all given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllArgumentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllArgumentsNamed(list of String) returns declaration with all given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withAllArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllArgumentsNamed(set of String) returns declaration with all given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withAllArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllArgumentsNamed(name) returns declaration without given argument`() {
        // given
        val name = "SampleName"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllArgumentsNamed(name)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllArgumentsNamed(String) returns declaration without all of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllArgumentsNamed(name1, name2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllArgumentsNamed(list of String) returns declaration without all of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(listOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = listOf(name1, name2)

        // when
        val sut = declarations.withoutAllArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllArgumentsNamed(set of String) returns declaration without all of given arguments`() {
        // given
        val name1 = "SampleName1"
        val name2 = "SampleName2"
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(setOf(name1, name2)) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgumentsWithAllNames(setOf(name1, name2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val names = setOf(name1, name2)

        // when
        val sut = declarations.withoutAllArgumentsNamed(names)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withArgument{} returns declaration with argument which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgument(predicate) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgument(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArgument(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutArgument{} returns declaration without argument which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasArgument(predicate) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasArgument(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArgument(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllArguments{} returns declaration with all arguments satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasAllArguments(predicate) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasAllArguments(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllArguments{} returns declaration with all arguments which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider =
            mockk {
                every { hasAllArguments(predicate) } returns true
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { hasAllArguments(predicate) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withArgument{} returns declaration with arguments which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoArgumentDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val argument1: KoArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val argument2: KoArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoArgumentProvider =
            mockk {
                every { arguments } returns listOf(argument1)
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { arguments } returns listOf(argument2)
            }
        val declaration3: KoArgumentProvider =
            mockk {
                every { arguments } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration3)
    }

    @Test
    fun `withoutArgument{} returns declaration without arguments which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (List<KoArgumentDeclaration>) -> Boolean =
            { it.all { argument -> argument.hasNameEndingWith(suffix) } }
        val argument1: KoArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns true
            }
        val argument2: KoArgumentDeclaration =
            mockk {
                every { hasNameEndingWith(suffix) } returns false
            }
        val declaration1: KoArgumentProvider =
            mockk {
                every { arguments } returns listOf(argument1)
            }
        val declaration2: KoArgumentProvider =
            mockk {
                every { arguments } returns listOf(argument2)
            }
        val declaration3: KoArgumentProvider =
            mockk {
                every { arguments } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
