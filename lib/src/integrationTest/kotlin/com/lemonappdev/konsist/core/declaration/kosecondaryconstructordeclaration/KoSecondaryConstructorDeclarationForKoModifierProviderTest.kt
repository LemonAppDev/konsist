package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoSecondaryConstructorDeclarationForKoModifierProviderTest {
    @Test
    fun `secondary-constructor-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-no-modifiers")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.modifiers.toList() shouldBeEqualTo emptyList()
            it.hasModifiers() shouldBeEqualTo false
            it.hasModifiers(OPEN) shouldBeEqualTo false
            it.hasModifiers(OPEN, DATA) shouldBeEqualTo false
            it.hasPublicModifier() shouldBeEqualTo false
            it.isPublicOrDefault() shouldBeEqualTo true
            it.hasPrivateModifier() shouldBeEqualTo false
            it.hasProtectedModifier() shouldBeEqualTo false
            it.hasInternalModifier() shouldBeEqualTo false
            it.hasEnumModifier() shouldBeEqualTo false
            it.hasSealedModifier() shouldBeEqualTo false
            it.hasInnerModifier() shouldBeEqualTo false
            it.hasValueModifier() shouldBeEqualTo false
            it.hasAnnotationModifier() shouldBeEqualTo false
            it.hasDataModifier() shouldBeEqualTo false
            it.hasActualModifier() shouldBeEqualTo false
            it.hasExpectModifier() shouldBeEqualTo false
            it.hasAbstractModifier() shouldBeEqualTo false
            it.hasOpenModifier() shouldBeEqualTo false
            it.hasFinalModifier() shouldBeEqualTo false
            it.hasVarargModifier() shouldBeEqualTo false
            it.hasNoInlineModifier() shouldBeEqualTo false
            it.hasCrossInlineModifier() shouldBeEqualTo false
            it.hasOperatorModifier() shouldBeEqualTo false
            it.hasInlineModifier() shouldBeEqualTo false
            it.hasTailrecModifier() shouldBeEqualTo false
            it.hasInfixModifier() shouldBeEqualTo false
            it.hasExternalModifier() shouldBeEqualTo false
            it.hasSuspendModifier() shouldBeEqualTo false
            it.hasOverrideModifier() shouldBeEqualTo false
            it.hasFunModifier() shouldBeEqualTo false
            it.hasLateinitModifier() shouldBeEqualTo false
            it.hasConstModifier() shouldBeEqualTo false
            it.hasCompanionModifier() shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `secondary-constructor-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.modifiers.toList() shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `secondary-constructor-has-public-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-public-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasPublicModifier() shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor-is-public-by-default`() {
        // given
        val sut = getSnippetFile("secondary-constructor-is-public-by-default")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.isPublicOrDefault() shouldBeEqualTo true
            it.hasPublicModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-private-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-private-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasPrivateModifier() shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-protected-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasProtectedModifier() shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-internal-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasInternalModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("secondary-constructor-has-modifier"),
            arguments("secondary-constructor-has-modifiers-and-annotation-with-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotation-without-parameter"),
            arguments("secondary-constructor-has-modifiers-and-annotations"),
        )
    }
}
