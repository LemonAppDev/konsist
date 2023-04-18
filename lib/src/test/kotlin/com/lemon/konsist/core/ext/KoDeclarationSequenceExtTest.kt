package com.lemon.konsist.core.ext

import com.lemon.konsist.core.declaration.KoDeclaration
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoDeclarationSequenceExtTest {
    @Test
    fun `withPublicModifier() returns declaration1 with public modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicModifier() returns declaration2 without public modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPublicModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPublicModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withPublicOrDefaultModifier() returns declaration1 with public or default modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPublicOrDefaultModifier() returns declaration2 without public or default modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { isPublicOrDefault() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPublicOrDefaultModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }@Test
    fun `withPrivateModifier() returns declaration1 with private modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutPrivateModifier() returns declaration2 without private modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasPrivateModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutPrivateModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withProtectedModifier() returns declaration1 with protected modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutProtectedModifier() returns declaration2 without protected modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasProtectedModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutProtectedModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInternalModifier() returns declaration1 with internal modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInternalModifier() returns declaration2 without internal modifier`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { hasInternalModifier() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { hasInternalModifier() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInternalModifier()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTopLevel() returns declaration1 which is top level declaration`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTopLevel() returns declaration2 which is not top level declaration`() {
        // given
        val declaration1: KoDeclaration = mockk {
            every { isTopLevel() } returns true
        }
        val declaration2: KoDeclaration = mockk {
            every { isTopLevel() } returns false
        }
        val declarations = sequenceOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTopLevel()

        // then
        sut.toList() shouldBeEqualTo listOf(declaration2)
    }
}