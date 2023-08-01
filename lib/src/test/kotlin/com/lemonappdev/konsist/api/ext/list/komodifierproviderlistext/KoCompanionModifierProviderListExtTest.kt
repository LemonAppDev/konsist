package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext


import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInnerModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoFunModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoConstModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoCompanionModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValueModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoSealedModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import withCompanionModifier
import withoutCompanionModifier

class KoCompanionModifierProviderListExtTest {
    @Test
    fun `withCompanionModifier() returns declaration with companion modifier`() {
        // given
        val declaration1: KoCompanionModifierProvider = mockk {
            every { hasCompanionModifier } returns true
        }
        val declaration2: KoCompanionModifierProvider = mockk {
            every { hasCompanionModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCompanionModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutCompanionModifier() returns declaration without companion modifier`() {
        // given
        val declaration1: KoCompanionModifierProvider = mockk {
            every { hasCompanionModifier } returns true
        }
        val declaration2: KoCompanionModifierProvider = mockk {
            every { hasCompanionModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
