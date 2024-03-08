package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoCrossInlineModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoCrossInlineModifierProviderListExtTest {
    @Test
    fun `withCrossInlineModifier() returns declaration with crossInline modifier`() {
        // given
        val declaration1: KoCrossInlineModifierProvider =
            mockk {
                every { hasCrossInlineModifier } returns true
            }
        val declaration2: KoCrossInlineModifierProvider =
            mockk {
                every { hasCrossInlineModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCrossInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutCrossInlineModifier() returns declaration without crossInline modifier`() {
        // given
        val declaration1: KoCrossInlineModifierProvider =
            mockk {
                every { hasCrossInlineModifier } returns true
            }
        val declaration2: KoCrossInlineModifierProvider =
            mockk {
                every { hasCrossInlineModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCrossInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
