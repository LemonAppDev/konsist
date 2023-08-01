package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoImplementationProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImplementationProviderListExtTest {
    @Test
    fun `withImplementation() returns declaration with implementation`() {
        // given
        val declaration1: KoImplementationProvider = mockk {
            every { hasImplementation } returns true
        }
        val declaration2: KoImplementationProvider = mockk {
            every { hasImplementation } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withImplementation()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutImplementation() returns declaration without implementation`() {
        // given
        val declaration1: KoImplementationProvider = mockk {
            every { hasImplementation } returns true
        }
        val declaration2: KoImplementationProvider = mockk {
            every { hasImplementation } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutImplementation()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
