package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInlineModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInlineModifierProviderListExtTest {
    @Test
    fun `withInlineModifier() returns declaration with inline modifier`() {
        // given
        val declaration1: KoInlineModifierProvider =
            mockk {
                every { hasInlineModifier } returns true
            }
        val declaration2: KoInlineModifierProvider =
            mockk {
                every { hasInlineModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInlineModifier() returns declaration without inline modifier`() {
        // given
        val declaration1: KoInlineModifierProvider =
            mockk {
                every { hasInlineModifier } returns true
            }
        val declaration2: KoInlineModifierProvider =
            mockk {
                every { hasInlineModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
