package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoTailrecModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTailrecModifierProviderListExtTest {
    @Test
    fun `withTailrecModifier() returns declaration with tailrec modifier`() {
        // given
        val declaration1: KoTailrecModifierProvider =
            mockk {
                every { hasTailrecModifier } returns true
            }
        val declaration2: KoTailrecModifierProvider =
            mockk {
                every { hasTailrecModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTailrecModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTailrecModifier() returns declaration without tailrec modifier`() {
        // given
        val declaration1: KoTailrecModifierProvider =
            mockk {
                every { hasTailrecModifier } returns true
            }
        val declaration2: KoTailrecModifierProvider =
            mockk {
                every { hasTailrecModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTailrecModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
