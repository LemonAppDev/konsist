package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoFunModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunModifierProviderListExtTest {
    @Test
    fun `withFunModifier() returns declaration with fun modifier`() {
        // given
        val declaration1: KoFunModifierProvider =
            mockk {
                every { hasFunModifier } returns true
            }
        val declaration2: KoFunModifierProvider =
            mockk {
                every { hasFunModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFunModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFunModifier() returns declaration without fun modifier`() {
        // given
        val declaration1: KoFunModifierProvider =
            mockk {
                every { hasFunModifier } returns true
            }
        val declaration2: KoFunModifierProvider =
            mockk {
                every { hasFunModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFunModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
