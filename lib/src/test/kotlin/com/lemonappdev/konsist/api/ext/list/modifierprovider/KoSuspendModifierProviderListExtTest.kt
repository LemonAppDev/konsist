package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoSuspendModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import withSuspendModifier
import withoutSuspendModifier

class KoSuspendModifierProviderListExtTest {
    @Test
    fun `withSuspendModifier() returns declaration with suspend modifier`() {
        // given
        val declaration1: KoSuspendModifierProvider =
            mockk {
                every { hasSuspendModifier } returns true
            }
        val declaration2: KoSuspendModifierProvider =
            mockk {
                every { hasSuspendModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSuspendModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSuspendModifier() returns declaration without suspend modifier`() {
        // given
        val declaration1: KoSuspendModifierProvider =
            mockk {
                every { hasSuspendModifier } returns true
            }
        val declaration2: KoSuspendModifierProvider =
            mockk {
                every { hasSuspendModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSuspendModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
