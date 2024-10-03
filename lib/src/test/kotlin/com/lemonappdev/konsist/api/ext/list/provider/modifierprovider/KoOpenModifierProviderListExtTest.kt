package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOpenModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoOpenModifierProviderListExtTest {
    @Test
    fun `withOpenModifier() returns declaration with open modifier`() {
        // given
        val declaration1: KoOpenModifierProvider =
            mockk {
                every { hasOpenModifier } returns true
            }
        val declaration2: KoOpenModifierProvider =
            mockk {
                every { hasOpenModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withOpenModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutOpenModifier() returns declaration without open modifier`() {
        // given
        val declaration1: KoOpenModifierProvider =
            mockk {
                every { hasOpenModifier } returns true
            }
        val declaration2: KoOpenModifierProvider =
            mockk {
                every { hasOpenModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOpenModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
