package com.lemonappdev.konsist.api.ext.sequence

import com.lemonappdev.konsist.api.provider.KoVarAndValProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVarAndValProviderSequenceExtTest {
    @Test
    fun `withVarModifier() returns declaration with var modifier`() {
        // given
        val declaration1: KoVarAndValProvider = mockk {
            every { isVar } returns true
        }
        val declaration2: KoVarAndValProvider = mockk {
            every { isVar } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVarModifier() returns declaration without var modifier`() {
        // given
        val declaration1: KoVarAndValProvider = mockk {
            every { isVar } returns true
        }
        val declaration2: KoVarAndValProvider = mockk {
            every { isVar } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVarModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withValModifier() returns declaration with val modifier`() {
        // given
        val declaration1: KoVarAndValProvider = mockk {
            every { isVal } returns true
        }
        val declaration2: KoVarAndValProvider = mockk {
            every { isVal } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutValModifier() returns declaration without val modifier`() {
        // given
        val declaration1: KoVarAndValProvider = mockk {
            every { isVal } returns true
        }
        val declaration2: KoVarAndValProvider = mockk {
            every { isVal } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutValModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
