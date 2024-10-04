package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoNoInlineModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoNoInlineModifierProviderListExtTest {
    @Test
    fun `withNoInlineModifier() returns declaration with noInline modifier`() {
        // given
        val declaration1: KoNoInlineModifierProvider =
            mockk {
                every { hasNoInlineModifier } returns true
            }
        val declaration2: KoNoInlineModifierProvider =
            mockk {
                every { hasNoInlineModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withNoInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutNoInlineModifier() returns declaration without noInline modifier`() {
        // given
        val declaration1: KoNoInlineModifierProvider =
            mockk {
                every { hasNoInlineModifier } returns true
            }
        val declaration2: KoNoInlineModifierProvider =
            mockk {
                every { hasNoInlineModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutNoInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
