package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoAnnotationModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationModifierProviderListExtTest {
    @Test
    fun `withAnnotationModifier() returns declaration with annotation modifier`() {
        // given
        val declaration1: KoAnnotationModifierProvider =
            mockk {
                every { hasAnnotationModifier } returns true
            }
        val declaration2: KoAnnotationModifierProvider =
            mockk {
                every { hasAnnotationModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotationModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotationModifier() returns declaration without annotation modifier`() {
        // given
        val declaration1: KoAnnotationModifierProvider =
            mockk {
                every { hasAnnotationModifier } returns true
            }
        val declaration2: KoAnnotationModifierProvider =
            mockk {
                every { hasAnnotationModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotationModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
