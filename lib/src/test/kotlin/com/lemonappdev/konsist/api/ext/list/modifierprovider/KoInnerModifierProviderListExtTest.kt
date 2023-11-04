package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInnerModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInnerModifierProviderListExtTest {
    @Test
    fun `withInnerModifier() returns declaration with inner modifier`() {
        // given
        val declaration1: KoInnerModifierProvider =
            mockk {
                every { hasInnerModifier } returns true
            }
        val declaration2: KoInnerModifierProvider =
            mockk {
                every { hasInnerModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInnerModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInnerModifier() returns declaration without inner modifier`() {
        // given
        val declaration1: KoInnerModifierProvider =
            mockk {
                every { hasInnerModifier } returns true
            }
        val declaration2: KoInnerModifierProvider =
            mockk {
                every { hasInnerModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInnerModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
