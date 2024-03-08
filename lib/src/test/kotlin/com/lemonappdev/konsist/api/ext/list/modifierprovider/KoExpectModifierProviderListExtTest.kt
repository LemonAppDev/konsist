package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoExpectModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoExpectModifierProviderListExtTest {
    @Test
    fun `withExpectModifier() returns declaration with expect modifier`() {
        // given
        val declaration1: KoExpectModifierProvider =
            mockk {
                every { hasExpectModifier } returns true
            }
        val declaration2: KoExpectModifierProvider =
            mockk {
                every { hasExpectModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExpectModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExpectModifier() returns declaration without expect modifier`() {
        // given
        val declaration1: KoExpectModifierProvider =
            mockk {
                every { hasExpectModifier } returns true
            }
        val declaration2: KoExpectModifierProvider =
            mockk {
                every { hasExpectModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExpectModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
