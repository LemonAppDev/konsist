package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoDataModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDataModifierProviderListExtTest {
    @Test
    fun `withDataModifier() returns declaration with data modifier`() {
        // given
        val declaration1: KoDataModifierProvider =
            mockk {
                every { hasDataModifier } returns true
            }
        val declaration2: KoDataModifierProvider =
            mockk {
                every { hasDataModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDataModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDataModifier() returns declaration without data modifier`() {
        // given
        val declaration1: KoDataModifierProvider =
            mockk {
                every { hasDataModifier } returns true
            }
        val declaration2: KoDataModifierProvider =
            mockk {
                every { hasDataModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDataModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
