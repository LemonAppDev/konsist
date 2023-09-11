package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.declaration.KoArgumentDeclaration
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoArgumentProvider
import com.lemonappdev.konsist.api.provider.KoImportProvider
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
        val declaration1: KoArgumentProvider = mockk {
            every { arguments } returns listOf(argument1, argument2)
        }
        val declaration2: KoArgumentProvider = mockk {
            every { arguments } returns listOf(argument3)
        }
        val declaration3: KoArgumentProvider = mockk {
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
        val declaration1: KoArgumentProvider = mockk {
            every { hasArguments() } returns true
        }
        val declaration2: KoArgumentProvider = mockk {
            every { hasArguments() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArguments()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutArguments() returns declaration without any argument`() {
        // given
        val declaration1: KoArgumentProvider = mockk {
            every { hasArguments() } returns true
        }
        val declaration2: KoArgumentProvider = mockk {
            every { hasArguments() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArguments()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllArguments{} returns declaration with all imports satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider = mockk {
            every { hasAllArguments(predicate) } returns true
        }
        val declaration2: KoArgumentProvider = mockk {
            every { hasAllArguments(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withArgument{} returns declaration with import which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider = mockk {
            every { hasArgument(predicate) } returns true
        }
        val declaration2: KoArgumentProvider = mockk {
            every { hasArgument(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withArgument(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllArguments{} returns declaration with all imports which not satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider = mockk {
            every { hasAllArguments(predicate) } returns true
        }
        val declaration2: KoArgumentProvider = mockk {
            every { hasAllArguments(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllArguments(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutArgument{} returns declaration without import which satisfy predicate`() {
        // given
        val suffix = "Name"
        val predicate: (KoArgumentDeclaration) -> Boolean = { it.hasNameEndingWith(suffix) }
        val declaration1: KoArgumentProvider = mockk {
            every { hasArgument(predicate) } returns true
        }
        val declaration2: KoArgumentProvider = mockk {
            every { hasArgument(predicate) } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutArgument(predicate)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
