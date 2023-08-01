package com.lemonappdev.konsist.api.ext.list.komodifierproviderlistext


import com.lemonappdev.konsist.api.provider.komodifierprovider.KoEnumModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoInnerModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoAnnotationModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoDataModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoOverrideModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoValueModifierProvider
import com.lemonappdev.konsist.api.provider.komodifierprovider.KoSealedModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoOverrideModifierProviderListExtTest {
    @Test
    fun `withOverrideModifier() returns declaration with override modifier`() {
        // given
        val declaration1: KoOverrideModifierProvider = mockk {
            every { hasOverrideModifier } returns true
        }
        val declaration2: KoOverrideModifierProvider = mockk {
            every { hasOverrideModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withOverrideModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutOverrideModifier() returns declaration without override modifier`() {
        // given
        val declaration1: KoOverrideModifierProvider = mockk {
            every { hasOverrideModifier } returns true
        }
        val declaration2: KoOverrideModifierProvider = mockk {
            every { hasOverrideModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOverrideModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
