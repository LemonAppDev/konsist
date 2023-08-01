package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext

import com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext.withValModifier
import com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext.withVarModifier
import com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext.withoutValModifier
import com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext.withoutVarModifier
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoVarModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVarModifierProviderListExtTest {
    @Test
    fun `withVarModifier() returns declaration with var modifier`() {
        // given
        val declaration1: KoVarModifierProvider = mockk {
            every { hasVarModifier } returns true
        }
        val declaration2: KoVarModifierProvider = mockk {
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
        val declaration1: KoVarModifierProvider = mockk {
            every { hasVarModifier } returns true
        }
        val declaration2: KoVarModifierProvider = mockk {
            every { hasVarModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVarModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
