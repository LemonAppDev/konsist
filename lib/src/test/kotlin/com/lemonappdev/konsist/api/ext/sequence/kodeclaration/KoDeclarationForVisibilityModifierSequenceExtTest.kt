package com.lemonappdev.konsist.api.ext.sequence.kodeclaration

import com.lemonappdev.konsist.api.ext.sequence.withInternalModifier
import com.lemonappdev.konsist.api.ext.sequence.withPrivateModifier
import com.lemonappdev.konsist.api.ext.sequence.withProtectedModifier
import com.lemonappdev.konsist.api.ext.sequence.withPublicModifier
import com.lemonappdev.konsist.api.ext.sequence.withPublicOrDefaultModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutInternalModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutPrivateModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutProtectedModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutPublicModifier
import com.lemonappdev.konsist.api.ext.sequence.withoutPublicOrDefaultModifier
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationForVisibilityModifierSequenceExtTest {
    @Test
    fun `withPublicModifier() returns declaration with public modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicModifier() returns declaration without public modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPublicOrDefaultModifier() returns declaration with public or default modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicOrDefaultModifier() returns declaration without public or default modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPrivateModifier() returns declaration with private modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPrivateModifier() returns declaration without private modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProtectedModifier() returns declaration with protected modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProtectedModifier() returns declaration without protected modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInternalModifier() returns declaration with internal modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInternalModifier() returns declaration without internal modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}
