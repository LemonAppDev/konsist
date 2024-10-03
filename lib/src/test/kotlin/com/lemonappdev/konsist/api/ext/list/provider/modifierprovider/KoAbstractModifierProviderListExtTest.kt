package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoAbstractModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAbstractModifierProviderListExtTest {
    @Test
    fun `withAbstractModifier() returns declaration with abstract modifier`() {
        // given
        val declaration1: KoAbstractModifierProvider =
            mockk {
                every { hasAbstractModifier } returns true
            }
        val declaration2: KoAbstractModifierProvider =
            mockk {
                every { hasAbstractModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAbstractModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAbstractModifier() returns declaration without abstract modifier`() {
        // given
        val declaration1: KoAbstractModifierProvider =
            mockk {
                every { hasAbstractModifier } returns true
            }
        val declaration2: KoAbstractModifierProvider =
            mockk {
                every { hasAbstractModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAbstractModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
