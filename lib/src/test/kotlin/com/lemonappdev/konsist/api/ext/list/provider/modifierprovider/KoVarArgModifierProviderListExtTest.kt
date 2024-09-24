package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVarArgModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVarArgModifierProviderListExtTest {
    @Test
    fun `withVarargModifier() returns declaration with vararg modifier`() {
        // given
        val declaration1: KoVarArgModifierProvider =
            mockk {
                every { hasVarArgModifier } returns true
            }
        val declaration2: KoVarArgModifierProvider =
            mockk {
                every { hasVarArgModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVarargModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVarargModifier() returns declaration without vararg modifier`() {
        // given
        val declaration1: KoVarArgModifierProvider =
            mockk {
                every { hasVarArgModifier } returns true
            }
        val declaration2: KoVarArgModifierProvider =
            mockk {
                every { hasVarArgModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVarargModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
