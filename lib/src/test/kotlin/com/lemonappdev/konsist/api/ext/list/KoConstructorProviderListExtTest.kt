package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoConstructorProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorProviderListExtTest {
    @Test
    fun `withConstructor() returns declaration with constructor`() {
        // given
        val declaration1: KoConstructorProvider = mockk {
            every { numConstructors } returns 1
        }
        val declaration2: KoConstructorProvider = mockk {
            every { numConstructors } returns 0
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstructor()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstructor() returns declaration without constructor`() {
        // given
        val declaration1: KoConstructorProvider = mockk {
            every { numConstructors } returns 1
        }
        val declaration2: KoConstructorProvider = mockk {
            every { numConstructors } returns 0
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstructor()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
