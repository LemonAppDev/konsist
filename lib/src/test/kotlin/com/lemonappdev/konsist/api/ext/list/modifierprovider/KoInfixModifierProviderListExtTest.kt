package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoInfixModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInfixModifierProviderListExtTest {
    @Test
    fun `withInfixModifier() returns declaration with infix modifier`() {
        // given
        val declaration1: KoInfixModifierProvider =
            mockk {
                every { hasInfixModifier } returns true
            }
        val declaration2: KoInfixModifierProvider =
            mockk {
                every { hasInfixModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInfixModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInfixModifier() returns declaration without infix modifier`() {
        // given
        val declaration1: KoInfixModifierProvider =
            mockk {
                every { hasInfixModifier } returns true
            }
        val declaration2: KoInfixModifierProvider =
            mockk {
                every { hasInfixModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInfixModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
