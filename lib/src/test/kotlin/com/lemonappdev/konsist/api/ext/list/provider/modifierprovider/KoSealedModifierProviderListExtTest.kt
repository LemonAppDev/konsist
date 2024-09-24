package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoSealedModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSealedModifierProviderListExtTest {
    @Test
    fun `withSealedModifier() returns declaration with sealed modifier`() {
        // given
        val declaration1: KoSealedModifierProvider =
            mockk {
                every { hasSealedModifier } returns true
            }
        val declaration2: KoSealedModifierProvider =
            mockk {
                every { hasSealedModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSealedModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSealedModifier() returns declaration without sealed modifier`() {
        // given
        val declaration1: KoSealedModifierProvider =
            mockk {
                every { hasSealedModifier } returns true
            }
        val declaration2: KoSealedModifierProvider =
            mockk {
                every { hasSealedModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSealedModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
