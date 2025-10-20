package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.KoHasDefaultNameProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoHasDefaultNameProviderListExtTest {
    @Test
    fun `withDefaultName() returns declaration using the default name`() {
        // given
        val declaration1: KoHasDefaultNameProvider =
            mockk {
                every { hasDefaultName } returns true
            }

        val declaration2: KoHasDefaultNameProvider =
            mockk {
                every { hasDefaultName } returns false
            }

        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDefaultName()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDefaultName() returns declaration not using the default name`() {
        // given
        val declaration1: KoHasDefaultNameProvider =
            mockk {
                every { hasDefaultName } returns true
            }

        val declaration2: KoHasDefaultNameProvider =
            mockk {
                every { hasDefaultName } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDefaultName()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
