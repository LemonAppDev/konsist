package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoGetterProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterProviderListExtTest {
    @Test
    fun `withGetter() returns declaration with getter`() {
        // given
        val declaration1: KoGetterProvider = mockk {
            every { hasGetter } returns true
        }
        val declaration2: KoGetterProvider = mockk {
            every { hasGetter } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withGetter()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutGetter() returns declaration without getter`() {
        // given
        val declaration1: KoGetterProvider = mockk {
            every { hasGetter } returns true
        }
        val declaration2: KoGetterProvider = mockk {
            every { hasGetter } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutGetter()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
