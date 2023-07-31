package com.lemonappdev.konsist.core.declaration.kopropertydeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.FINAL
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoPropertyDeclarationForKoModifierProviderTest {
    @Test
    fun `property-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-no-modifiers")
            .properties()
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
    fun `property-has-protected-and-open-modifiers`() {
        // given
        val sut = getSnippetFile("property-has-protected-and-open-modifiers")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo true
            hasModifiers(FINAL) shouldBeEqualTo false
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo true
            hasModifiers(OPEN, PROTECTED) shouldBeEqualTo true
            hasModifiers(PROTECTED, FINAL) shouldBeEqualTo false
            hasModifiers(FINAL, OPEN, PROTECTED) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `property-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .properties(includeNested = true)
            .first()

        // then
        sut.modifiers.toList() shouldBeEqualTo modifiers
    }

    @Test
    fun `property-has-public-modifier`() {
        // given
        val sut = getSnippetFile("property-has-public-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasPublicModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-is-public-by-default`() {
        // given
        val sut = getSnippetFile("property-is-public-by-default")
            .properties(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-private-modifier`() {
        // given
        val sut = getSnippetFile("property-has-private-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasPrivateModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("property-has-protected-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasProtectedModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("property-has-internal-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasInternalModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-lateinit-modifier`() {
        // given
        val sut = getSnippetFile("property-has-lateinit-modifier")
            .properties()
            .first()

        // then
        sut.hasLateinitModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-override-modifier`() {
        // given
        val sut = getSnippetFile("property-has-override-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasOverrideModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-abstract-modifier`() {
        // given
        val sut = getSnippetFile("property-has-abstract-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasAbstractModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-open-modifier`() {
        // given
        val sut = getSnippetFile("property-has-open-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasOpenModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-final-modifier`() {
        // given
        val sut = getSnippetFile("property-has-final-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasFinalModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-is-const`() {
        // given
        val sut = getSnippetFile("property-is-const")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasConstModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("property-has-actual-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `property-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("property-has-expect-modifier")
            .properties(includeNested = true)
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopropertydeclaration/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("property-has-modifiers-and-annotation-with-parameter", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotation-without-parameter", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-annotation-and-comment", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotations", listOf(PROTECTED, OPEN)),
            arguments("property-has-modifiers-and-annotation-with-angle-brackets", listOf(PROTECTED, OPEN)),
        )
    }
}
