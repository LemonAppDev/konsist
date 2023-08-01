package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVarAndValProviderListExtTest {
    @Test
    fun `withVarModifier() returns declaration with var modifier`() {
        // given
        val declaration1: KoValModifierProvider = mockk {
            every { hasVarModifier } returns true
        }
        val declaration2: KoValModifierProvider = mockk {
            every { hasVarModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVarModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVarModifier() returns declaration without var modifier`() {
        // given
        val declaration1: KoValModifierProvider = mockk {
            every { hasVarModifier } returns true
        }
        val declaration2: KoValModifierProvider = mockk {
            every { hasVarModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVarModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withValModifier() returns declaration with val modifier`() {
        // given
        val declaration1: KoValModifierProvider = mockk {
            every { hasValModifier } returns true
        }
        val declaration2: KoValModifierProvider = mockk {
            every { hasValModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withValModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutValModifier() returns declaration without val modifier`() {
        // given
        val declaration1: KoValModifierProvider = mockk {
            every { hasValModifier } returns true
        }
        val declaration2: KoValModifierProvider = mockk {
            every { hasValModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutValModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
