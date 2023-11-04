package com.lemonappdev.konsist.api.ext.list.modifierprovider

import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.provider.modifier.KoModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoModifierProviderListExtTest {
    @Test
    fun `modifiers returns modifiers from all declarations`() {
        // given
        val modifier1: KoModifier = mockk()
        val modifier2: KoModifier = mockk()
        val modifier3: KoModifier = mockk()
        val declaration1: KoModifierProvider =
            mockk {
                every { modifiers } returns listOf(modifier1, modifier2)
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { modifiers } returns listOf(modifier3)
            }
        val declaration3: KoModifierProvider =
            mockk {
                every { modifiers } returns emptyList()
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.modifiers

        // then
        sut shouldBeEqualTo listOf(modifier1, modifier2, modifier3)
    }

    @Test
    fun `withModifiers() returns declaration with modifier`() {
        // given
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifiers() } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifiers() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifiers()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutModifiers() returns declaration without modifier`() {
        // given
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifiers() } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifiers() } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifiers()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withModifier(String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifier(modifier1, modifier2) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(modifier1, modifier2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifier(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutModifier(String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifier(modifier1, modifier2) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(modifier1, modifier2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifier(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllModifiers(String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasAllModifiers(modifier1, modifier2) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(modifier1, modifier2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllModifiers(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAllModifiers(String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasAllModifiers(modifier1, modifier2) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(modifier1, modifier2) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllModifiers(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeModifiers(String) returns declaration with given modifier`() {
        // given
        val modifier = PROTECTED
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeModifiers(modifier)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeModifiers(String) returns declarations with at least one of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier1) } returns true
                every { hasModifiers(modifier2) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier1) } returns false
                every { hasModifiers(modifier2) } returns true
            }
        val declaration3: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier1) } returns false
                every { hasModifiers(modifier2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeModifiers(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration1, declaration2)
    }

    @Test
    fun `withoutSomeModifiers(String) returns declaration with given modifier`() {
        // given
        val modifier = PROTECTED
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSomeModifiers(modifier)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutSomeModifiers(String) returns declarations with at least one of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier1) } returns true
                every { hasModifiers(modifier2) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier1) } returns false
                every { hasModifiers(modifier2) } returns true
            }
        val declaration3: KoModifierProvider =
            mockk {
                every { hasModifiers(modifier1) } returns false
                every { hasModifiers(modifier2) } returns false
            }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeModifiers(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }
}
