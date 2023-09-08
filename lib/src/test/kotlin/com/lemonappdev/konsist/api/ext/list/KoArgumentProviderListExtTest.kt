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
}
