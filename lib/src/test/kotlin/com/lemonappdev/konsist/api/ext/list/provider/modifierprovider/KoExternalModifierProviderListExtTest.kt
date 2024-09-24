package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoExternalModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExternalModifierProviderListExtTest {
    @Test
    fun `withExternalModifier() returns declaration with external modifier`() {
        // given
        val declaration1: KoExternalModifierProvider =
            mockk {
                every { hasExternalModifier } returns true
            }
        val declaration2: KoExternalModifierProvider =
            mockk {
                every { hasExternalModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalModifier() returns declaration without external modifier`() {
        // given
        val declaration1: KoExternalModifierProvider =
            mockk {
                every { hasExternalModifier } returns true
            }
        val declaration2: KoExternalModifierProvider =
            mockk {
                every { hasExternalModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
