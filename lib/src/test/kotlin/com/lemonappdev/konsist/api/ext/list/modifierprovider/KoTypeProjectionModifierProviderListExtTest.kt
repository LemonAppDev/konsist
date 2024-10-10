package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoTypeProjectionModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeProjectionModifierProviderListExtTest {
    @Test
    fun `withOutModifier() returns declaration with out modifier`() {
        // given
        val declaration1: KoTypeProjectionModifierProvider =
            mockk {
                every { hasOutModifier } returns true
            }
        val declaration2: KoTypeProjectionModifierProvider =
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
        val declaration1: KoTypeProjectionModifierProvider =
            mockk {
                every { hasOutModifier } returns true
            }
        val declaration2: KoTypeProjectionModifierProvider =
            mockk {
                every { hasOutModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOutModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInModifier() returns declaration with in modifier`() {
        // given
        val declaration1: KoTypeProjectionModifierProvider =
            mockk {
                every { hasInModifier } returns true
            }
        val declaration2: KoTypeProjectionModifierProvider =
            mockk {
                every { hasInModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInModifier() returns declaration without in modifier`() {
        // given
        val declaration1: KoTypeProjectionModifierProvider =
            mockk {
                every { hasInModifier } returns true
            }
        val declaration2: KoTypeProjectionModifierProvider =
            mockk {
                every { hasInModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
