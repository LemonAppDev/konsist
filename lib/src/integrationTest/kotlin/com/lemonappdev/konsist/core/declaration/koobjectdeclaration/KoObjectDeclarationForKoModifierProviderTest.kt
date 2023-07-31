package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoObjectDeclarationForKoModifierProviderTest {
    @Test
    fun `object-without-modifiers`() {
        // given
        val sut = getSnippetFile("object-without-modifiers")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            modifiers.toList() shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA) shouldBeEqualTo false
            hasPublicModifier() shouldBeEqualTo false
            isPublicOrDefault() shouldBeEqualTo true
            hasPrivateModifier() shouldBeEqualTo false
            hasProtectedModifier() shouldBeEqualTo false
            hasInternalModifier() shouldBeEqualTo false
            hasEnumModifier() shouldBeEqualTo false
            hasSealedModifier() shouldBeEqualTo false
            hasInnerModifier() shouldBeEqualTo false
            hasValueModifier() shouldBeEqualTo false
            hasAnnotationModifier() shouldBeEqualTo false
            hasDataModifier() shouldBeEqualTo false
            hasActualModifier() shouldBeEqualTo false
            hasExpectModifier() shouldBeEqualTo false
            hasAbstractModifier() shouldBeEqualTo false
            hasOpenModifier() shouldBeEqualTo false
            hasFinalModifier() shouldBeEqualTo false
            hasVarargModifier() shouldBeEqualTo false
            hasNoInlineModifier() shouldBeEqualTo false
            hasCrossInlineModifier() shouldBeEqualTo false
            hasOperatorModifier() shouldBeEqualTo false
            hasInlineModifier() shouldBeEqualTo false
            hasTailrecModifier() shouldBeEqualTo false
            hasInfixModifier() shouldBeEqualTo false
            hasExternalModifier() shouldBeEqualTo false
            hasSuspendModifier() shouldBeEqualTo false
            hasOverrideModifier() shouldBeEqualTo false
            hasFunModifier() shouldBeEqualTo false
            hasLateinitModifier() shouldBeEqualTo false
            hasConstModifier() shouldBeEqualTo false
            hasCompanionModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-private-and-data-modifiers`() {
        // given
        val sut = getSnippetFile("object-has-private-and-data-modifiers")
            .objects(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PRIVATE) shouldBeEqualTo true
            hasModifiers(DATA) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(PRIVATE, DATA) shouldBeEqualTo true
            hasModifiers(DATA, PRIVATE) shouldBeEqualTo true
            hasModifiers(PRIVATE, OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, DATA, PRIVATE) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `object-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .objects(includeNested = true)
            .first()

        // then
        sut.modifiers.toList() shouldBeEqualTo modifiers
    }

    @Test
    fun `object-has-public-modifier`() {
        // given
        val sut = getSnippetFile("object-has-public-modifier")
            .objects(includeNested = true)
            .first()

        // then
        sut.hasPublicModifier() shouldBeEqualTo true
    }

    @Test
    fun `object-is-public-by-default`() {
        // given
        val sut = getSnippetFile("object-is-public-by-default")
            .objects(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-private-modifier`() {
        // given
        val sut = getSnippetFile("object-has-private-modifier")
            .objects(includeNested = true)
            .first()

        // then
        sut.hasPrivateModifier() shouldBeEqualTo true
    }

    @Test
    fun `object-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("object-has-protected-modifier")
            .objects(includeNested = true)
            .first()

        // then
        sut.hasProtectedModifier() shouldBeEqualTo true
    }

    @Test
    fun `object-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("object-has-internal-modifier")
            .objects(includeNested = true)
            .first()

        // then
        sut.hasInternalModifier() shouldBeEqualTo true
    }

    @Test
    fun `data-object`() {
        // given
        val sut = getSnippetFile("data-object")
            .objects()
            .first()

        // then
        sut.hasDataModifier() shouldBeEqualTo true
    }

    @Test
    fun `companion-object`() {
        // given
        val sut = getSnippetFile("companion-object")
            .objects(includeNested = true)
            .first()

        // then
        sut.hasCompanionModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("object-has-modifiers-and-annotation-with-parameter", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotation-without-parameter", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-annotation-and-comment", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotations", listOf(PRIVATE, DATA)),
            arguments("object-has-modifiers-and-annotation-with-angle-brackets", listOf(PRIVATE, DATA)),
        )
    }
}
