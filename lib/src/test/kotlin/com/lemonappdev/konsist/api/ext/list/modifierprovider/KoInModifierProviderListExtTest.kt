package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInModifierProviderListExtTest {
    @Test
    fun `withInModifier() returns declaration with out modifier`() {
        // given
        val declaration1: KoInModifierProvider =
            mockk {
                every { hasInModifier } returns true
            }
        val declaration2: KoInModifierProvider =
            mockk {
                every { hasInModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInModifier() returns declaration without out modifier`() {
        // given
        val declaration1: KoInModifierProvider =
            mockk {
                every { hasInModifier } returns true
            }
        val declaration2: KoInModifierProvider =
            mockk {
                every { hasInModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
