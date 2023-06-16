package com.lemonappdev.konsist.api.ext.sequence.kodeclaration

import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.ext.sequence.withModifiers
import com.lemonappdev.konsist.api.ext.sequence.withSomeModifiers
import com.lemonappdev.konsist.api.ext.sequence.withoutModifiers
import com.lemonappdev.konsist.core.declaration.KoDeclarationImpl
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForModifierSequenceExtTest {
    @Test
    fun `withModifiers() returns declaration with modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifiers()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutModifiers() returns declaration without modifier`() {
        // given
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifiers()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withModifiers(String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutModifiers(String) returns declaration without any of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1, modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSomeModifiers(String) returns declaration with given modifier`() {
        // given
        val modifier = PROTECTED
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withSomeModifiers(modifier)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withSomeModifiers(String) returns declarations with at least one of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1) } returns true
            every { hasModifiers(modifier2) } returns true
        }
        val declaration2: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns true
        }
        val declaration3: KoDeclarationImpl = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withSomeModifiers(modifier1, modifier2)

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1, declaration2)
    }
}
