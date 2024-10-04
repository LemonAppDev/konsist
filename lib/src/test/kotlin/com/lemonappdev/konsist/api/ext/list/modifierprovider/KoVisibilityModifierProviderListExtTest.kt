package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.provider.modifier.KoVisibilityModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoVisibilityModifierProviderListExtTest {
    @Test
    fun `withPublicModifier() returns declaration with public modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicModifier() returns declaration without public modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPublicOrDefaultModifier() returns declaration with public or default modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicOrDefaultModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicOrDefaultModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicOrDefaultModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicOrDefaultModifier() returns declaration without public or default modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicOrDefaultModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasPublicOrDefaultModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicOrDefaultModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPrivateModifier() returns declaration with private modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasPrivateModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasPrivateModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withPrivateModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPrivateModifier() returns declaration without private modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasPrivateModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasPrivateModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPrivateModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProtectedModifier() returns declaration with protected modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasProtectedModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasProtectedModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withProtectedModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProtectedModifier() returns declaration without protected modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasProtectedModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasProtectedModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProtectedModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInternalModifier() returns declaration with internal modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasInternalModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasInternalModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInternalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInternalModifier() returns declaration without internal modifier`() {
        // given
        val declaration1: KoVisibilityModifierProvider =
            mockk {
                every { hasInternalModifier } returns true
            }
        val declaration2: KoVisibilityModifierProvider =
            mockk {
                every { hasInternalModifier } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInternalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
