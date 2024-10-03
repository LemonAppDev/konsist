package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoFinalModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFinalModifierProviderListExtTest {
    @Test
    fun `withFinalModifier() returns declaration with final modifier`() {
        // given
        val declaration1: KoFinalModifierProvider =
            mockk {
                every { hasFinalModifier } returns true
            }
        val declaration2: KoFinalModifierProvider =
            mockk {
                every { hasFinalModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFinalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFinalModifier() returns declaration without final modifier`() {
        // given
        val declaration1: KoFinalModifierProvider =
            mockk {
                every { hasFinalModifier } returns true
            }
        val declaration2: KoFinalModifierProvider =
            mockk {
                every { hasFinalModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFinalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
