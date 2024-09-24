package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOperatorModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoOperatorModifierProviderListExtTest {
    @Test
    fun `withOperatorModifier() returns declaration with operator modifier`() {
        // given
        val declaration1: KoOperatorModifierProvider =
            mockk {
                every { hasOperatorModifier } returns true
            }
        val declaration2: KoOperatorModifierProvider =
            mockk {
                every { hasOperatorModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withOperatorModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutOperatorModifier() returns declaration without operator modifier`() {
        // given
        val declaration1: KoOperatorModifierProvider =
            mockk {
                every { hasOperatorModifier } returns true
            }
        val declaration2: KoOperatorModifierProvider =
            mockk {
                every { hasOperatorModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOperatorModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
