package com.lemonappdev.konsist.api.ext.list.provider.modifierprovider

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
    fun `withModifier(empty list) returns declaration with modifier`() {
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
        val sut = declarations.withModifier(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withModifier(empty set) returns declaration with modifier`() {
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
        val sut = declarations.withModifier(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllModifiers(empty list) returns declaration with modifier`() {
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
        val sut = declarations.withAllModifiers(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllModifiers(empty set) returns declaration with modifier`() {
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
        val sut = declarations.withAllModifiers(emptySet())

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
    fun `withoutModifier(empty list) returns declaration without modifier`() {
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
        val sut = declarations.withoutModifier(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutModifier(empty set) returns declaration without modifier`() {
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
        val sut = declarations.withoutModifier(emptySet())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllModifiers(empty list) returns declaration without modifier`() {
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
        val sut = declarations.withoutAllModifiers(emptyList())

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllModifiers(empty set) returns declaration without modifier`() {
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
        val sut = declarations.withoutAllModifiers(emptySet())

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
                every { hasModifier(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifier(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withModifier(list of String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifier(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = listOf(modifier1, modifier2)

        // when
        val sut = declarations.withModifier(modifiers)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withModifier(set of String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifier(setOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(setOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = setOf(modifier1, modifier2)

        // when
        val sut = declarations.withModifier(modifiers)

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
                every { hasModifier(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifier(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutModifier(list of String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifier(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = listOf(modifier1, modifier2)

        // when
        val sut = declarations.withoutModifier(modifiers)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutModifier(set of String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasModifier(setOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasModifier(setOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = setOf(modifier1, modifier2)

        // when
        val sut = declarations.withoutModifier(modifiers)

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
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAllModifiers(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllModifiers(list of String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = listOf(modifier1, modifier2)

        // when
        val sut = declarations.withAllModifiers(modifiers)

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withAllModifiers(set of String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasAllModifiers(setOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(setOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = setOf(modifier1, modifier2)

        // when
        val sut = declarations.withAllModifiers(modifiers)

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
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAllModifiers(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllModifiers(list of String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(listOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = listOf(modifier1, modifier2)

        // when
        val sut = declarations.withoutAllModifiers(modifiers)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withoutAllModifiers(set of String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider =
            mockk {
                every { hasAllModifiers(setOf(modifier1, modifier2)) } returns true
            }
        val declaration2: KoModifierProvider =
            mockk {
                every { hasAllModifiers(setOf(modifier1, modifier2)) } returns false
            }
        val declarations = listOf(declaration1, declaration2)
        val modifiers = setOf(modifier1, modifier2)

        // when
        val sut = declarations.withoutAllModifiers(modifiers)

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
