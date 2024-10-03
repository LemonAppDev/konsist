package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoEnumModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumModifierProviderListExtTest {
    @Test
    fun `withEnumModifier() returns declaration with enum modifier`() {
        // given
        val declaration1: KoEnumModifierProvider =
            mockk {
                every { hasEnumModifier } returns true
            }
        val declaration2: KoEnumModifierProvider =
            mockk {
                every { hasEnumModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutEnumModifier() returns declaration without enum modifier`() {
        // given
        val declaration1: KoEnumModifierProvider =
            mockk {
                every { hasEnumModifier } returns true
            }
        val declaration2: KoEnumModifierProvider =
            mockk {
                every { hasEnumModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
