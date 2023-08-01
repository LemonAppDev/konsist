package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext


import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInnerModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOperatorModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValueModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoSealedModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoOperatorModifierProviderListExtTest {
    @Test
    fun `withOperatorModifier() returns declaration with operator modifier`() {
        // given
        val declaration1: KoOperatorModifierProvider = mockk {
            every { hasOperatorModifier } returns true
        }
        val declaration2: KoOperatorModifierProvider = mockk {
            every { hasOperatorModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withOperatorModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutOperatorModifier() returns declaration without operator modifier`() {
        // given
        val declaration1: KoOperatorModifierProvider = mockk {
            every { hasOperatorModifier } returns true
        }
        val declaration2: KoOperatorModifierProvider = mockk {
            every { hasOperatorModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOperatorModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
