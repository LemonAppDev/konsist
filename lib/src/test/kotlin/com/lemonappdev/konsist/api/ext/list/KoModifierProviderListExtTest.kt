package com.lemonappdev.konsist.api.ext.list

import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.provider.KoModifierProvider
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@Suppress("detekt.LargeClass")
class KoModifierProviderListExtTest {
    @Test
    fun `withModifiers() returns declaration with modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers() } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasModifiers() } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutModifiers()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAllModifiers(String) returns declaration with all of given modifiers`() {
        // given
        val modifier1 = PROTECTED
        val modifier2 = OPEN
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers(modifier1, modifier2) } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasModifiers(modifier1, modifier2) } returns false
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
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers(modifier1, modifier2) } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasModifiers(modifier1, modifier2) } returns false
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
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers(modifier) } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers(modifier1) } returns true
            every { hasModifiers(modifier2) } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns true
        }
        val declaration3: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers(modifier) } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasModifiers(modifier1) } returns true
            every { hasModifiers(modifier2) } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns true
        }
        val declaration3: KoModifierProvider = mockk {
            every { hasModifiers(modifier1) } returns false
            every { hasModifiers(modifier2) } returns false
        }
        val declarations = listOf(declaration1, declaration2, declaration3)

        // when
        val sut = declarations.withoutSomeModifiers(modifier1, modifier2)

        // then
        sut shouldBeEqualTo listOf(declaration3)
    }

    @Test
    fun `withPublicModifier() returns declaration with public modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasPublicModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasPublicModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { isPublicOrDefault } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { isPublicOrDefault } returns false
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
        val declaration1: KoModifierProvider = mockk {
            every { isPublicOrDefault } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { isPublicOrDefault } returns false
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
        val declaration1: KoModifierProvider = mockk {
            every { hasPrivateModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasPrivateModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasProtectedModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasProtectedModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasInternalModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasInternalModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInternalModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInternalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withEnumModifier() returns declaration with enum modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasEnumModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasEnumModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withEnumModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutEnumModifier() returns declaration without enum modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasEnumModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasEnumModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutEnumModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSealedModifier() returns declaration with sealed modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasSealedModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasSealedModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSealedModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSealedModifier() returns declaration without sealed modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasSealedModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasSealedModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSealedModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInnerModifier() returns declaration with inner modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInnerModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInnerModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInnerModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInnerModifier() returns declaration without inner modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInnerModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInnerModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInnerModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withValueModifier() returns declaration with value modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasValueModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasValueModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withValueModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutValueModifier() returns declaration without value modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasValueModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasValueModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutValueModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAnnotationModifier() returns declaration with annotation modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasAnnotationModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasAnnotationModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAnnotationModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAnnotationModifier() returns declaration without annotation modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasAnnotationModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasAnnotationModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAnnotationModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withDataModifier() returns declaration with data modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasDataModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasDataModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withDataModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutDataModifier() returns declaration without data modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasDataModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasDataModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutDataModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withActualModifier() returns declaration with actual modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasActualModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasActualModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withActualModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutActualModifier() returns declaration without actual modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasActualModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasActualModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutActualModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExpectModifier() returns declaration with expect modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasExpectModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasExpectModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExpectModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExpectModifier() returns declaration without expect modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasExpectModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasExpectModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExpectModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withAbstractModifier() returns declaration with abstract modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasAbstractModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasAbstractModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withAbstractModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutAbstractModifier() returns declaration without abstract modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasAbstractModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasAbstractModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutAbstractModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withOpenModifier() returns declaration with open modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasOpenModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasOpenModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withOpenModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutOpenModifier() returns declaration without open modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasOpenModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasOpenModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOpenModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withFinalModifier() returns declaration with final modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasFinalModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasFinalModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFinalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFinalModifier() returns declaration without final modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasFinalModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasFinalModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFinalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withVarargModifier() returns declaration with vararg modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasVarargModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasVarargModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withVarargModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutVarargModifier() returns declaration without vararg modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasVarargModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasVarargModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutVarargModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withNoInlineModifier() returns declaration with noInline modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasNoInlineModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasNoInlineModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withNoInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutNoInlineModifier() returns declaration without noInline modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasNoInlineModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasNoInlineModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutNoInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withCrossInlineModifier() returns declaration with crossInline modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasCrossInlineModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasCrossInlineModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withCrossInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutCrossInlineModifier() returns declaration without crossInline modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasCrossInlineModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasCrossInlineModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCrossInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withOperatorModifier() returns declaration with operator modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasOperatorModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasOperatorModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasOperatorModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOperatorModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInlineModifier() returns declaration with inline modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInlineModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInlineModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInlineModifier() returns declaration without inline modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInlineModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInlineModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInlineModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withTailrecModifier() returns declaration with tailrec modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasTailrecModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasTailrecModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withTailrecModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutTailrecModifier() returns declaration without tailrec modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasTailrecModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasTailrecModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutTailrecModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withInfixModifier() returns declaration with infix modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInfixModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInfixModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withInfixModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutInfixModifier() returns declaration without infix modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasInfixModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasInfixModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutInfixModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withExternalModifier() returns declaration with external modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasExternalModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasExternalModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withExternalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutExternalModifier() returns declaration without external modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasExternalModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasExternalModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutExternalModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withSuspendModifier() returns declaration with suspend modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasSuspendModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasSuspendModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withSuspendModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutSuspendModifier() returns declaration without suspend modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasSuspendModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasSuspendModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutSuspendModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withOverrideModifier() returns declaration with override modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasOverrideModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasOverrideModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasOverrideModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutOverrideModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withFunModifier() returns declaration with fun modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasFunModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasFunModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withFunModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutFunModifier() returns declaration without fun modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasFunModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasFunModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutFunModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withLateinitModifier() returns declaration with lateinit modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasLateinitModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasLateinitModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withLateinitModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutLateinitModifier() returns declaration without lateinit modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasLateinitModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasLateinitModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutLateinitModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withConstModifier() returns declaration with const modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasConstModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasConstModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withConstModifier()

        // then
        sut shouldBeEqualTo listOf(declaration1)
    }

    @Test
    fun `withoutConstModifier() returns declaration without const modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasConstModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasConstModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutConstModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }

    @Test
    fun `withCompanionModifier() returns declaration with companion modifier`() {
        // given
        val declaration1: KoModifierProvider = mockk {
            every { hasCompanionModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
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
        val declaration1: KoModifierProvider = mockk {
            every { hasCompanionModifier } returns true
        }
        val declaration2: KoModifierProvider = mockk {
            every { hasCompanionModifier } returns false
        }
        val declarations = listOf(declaration1, declaration2)

        // when
        val sut = declarations.withoutCompanionModifier()

        // then
        sut shouldBeEqualTo listOf(declaration2)
    }
}
