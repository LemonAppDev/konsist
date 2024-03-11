package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoValueModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoValueModifierProviderListExtTest {
    @Test
    fun `withValueModifier() returns declaration with value modifier`() {
        // given
        val declaration1: KoValueModifierProvider =
            mockk {
                every { hasValueModifier } returns true
            }
        val declaration2: KoValueModifierProvider =
            mockk {
                every { hasValueModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withValueModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutValueModifier() returns declaration without value modifier`() {
        // given
        val declaration1: KoValueModifierProvider =
            mockk {
                every { hasValueModifier } returns true
            }
        val declaration2: KoValueModifierProvider =
            mockk {
                every { hasValueModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutValueModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
