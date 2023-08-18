package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasTestProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoHasTestProviderListExtTest {
    @Test
    fun `withTest() returns declaration with test`() {
        // given
        val declaration1: KoHasTestProvider = mockk {
            every { hasTest() } returns true
        }
        val declaration2: KoHasTestProvider = mockk {
            every { hasTest() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTest()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTest() returns declaration without test`() {
        // given
        val declaration1: KoHasTestProvider = mockk {
            every { hasTest() } returns true
        }
        val declaration2: KoHasTestProvider = mockk {
            every { hasTest() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTest()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
