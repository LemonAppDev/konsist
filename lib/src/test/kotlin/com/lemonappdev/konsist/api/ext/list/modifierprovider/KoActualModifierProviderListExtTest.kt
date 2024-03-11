package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoActualModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoActualModifierProviderListExtTest {
    @Test
    fun `withActualModifier() returns declaration with actual modifier`() {
        // given
        val declaration1: KoActualModifierProvider =
            mockk {
                every { hasActualModifier } returns true
            }
        val declaration2: KoActualModifierProvider =
            mockk {
                every { hasActualModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withActualModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutActualModifier() returns declaration without actual modifier`() {
        // given
        val declaration1: KoActualModifierProvider =
            mockk {
                every { hasActualModifier } returns true
            }
        val declaration2: KoActualModifierProvider =
            mockk {
                every { hasActualModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutActualModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
