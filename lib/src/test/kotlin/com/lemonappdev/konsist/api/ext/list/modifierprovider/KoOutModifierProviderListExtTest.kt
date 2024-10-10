package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoOutModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoOutModifierProviderListExtTest {
    @Test
    fun `withOutModifier() returns declaration with out modifier`() {
        // given
        val declaration1: KoOutModifierProvider =
            mockk {
                every { hasOutModifier } returns true
            }
        val declaration2: KoOutModifierProvider =
            mockk {
                every { hasOutModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withOutModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutOutModifier() returns declaration without out modifier`() {
        // given
        val declaration1: KoOutModifierProvider =
            mockk {
                every { hasOutModifier } returns true
            }
        val declaration2: KoOutModifierProvider =
            mockk {
                every { hasOutModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOutModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
