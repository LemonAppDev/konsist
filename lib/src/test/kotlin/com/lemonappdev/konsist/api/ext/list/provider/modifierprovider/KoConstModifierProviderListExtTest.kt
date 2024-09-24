package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoConstModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstModifierProviderListExtTest {
    @Test
    fun `withConstModifier() returns declaration with const modifier`() {
        // given
        val declaration1: KoConstModifierProvider =
            mockk {
                every { hasConstModifier } returns true
            }
        val declaration2: KoConstModifierProvider =
            mockk {
                every { hasConstModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstModifier() returns declaration without const modifier`() {
        // given
        val declaration1: KoConstModifierProvider =
            mockk {
                every { hasConstModifier } returns true
            }
        val declaration2: KoConstModifierProvider =
            mockk {
                every { hasConstModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
