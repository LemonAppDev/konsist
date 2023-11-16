package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasTestClassProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Deprecated("Will be removed in v1.0.0")
class KoHasTestClassProviderListExtTest {
    @Test
    fun `withTestClass() returns declaration with test`() {
        // given
        val declaration1: KoHasTestClassProvider = mockk {
            every { hasTestClass() } returns true
        }
        val declaration2: KoHasTestClassProvider = mockk {
            every { hasTestClass() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTestClass()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTestClass() returns declaration without test`() {
        // given
        val declaration1: KoHasTestClassProvider = mockk {
            every { hasTestClass() } returns true
        }
        val declaration2: KoHasTestClassProvider = mockk {
            every { hasTestClass() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTestClass()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
