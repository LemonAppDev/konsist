package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoModifierProviderTest {
    @Test
    fun `function-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-no-modifiers")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            modifiers.toList() shouldBeEqualTo emptyList()
            hasModifiers() shouldBeEqualTo false
            hasModifiers(KoModifier.OPEN) shouldBeEqualTo false
            hasModifiers(KoModifier.OPEN, KoModifier.DATA) shouldBeEqualTo false
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
    fun `function-has-operator-modifier`() {
        // given
        val sut = getSnippetFile("function-has-operator-modifier")
            .functions()
            .first()

        // then
        sut.hasOperatorModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-inline-modifier`() {
        // given
        val sut = getSnippetFile("function-has-inline-modifier")
            .functions()
            .first()

        // then
        sut.hasInlineModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-tailrec-modifier`() {
        // given
        val sut = getSnippetFile("function-has-tailrec-modifier")
            .functions()
            .first()

        // then
        sut.hasTailrecModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-infix-modifier`() {
        // given
        val sut = getSnippetFile("function-has-infix-modifier")
            .functions()
            .first()

        // then
        sut.hasInfixModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-external-modifier`() {
        // given
        val sut = getSnippetFile("function-has-external-modifier")
            .functions()
            .first()

        // then
        sut.hasExternalModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-suspend-modifier`() {
        // given
        val sut = getSnippetFile("function-has-suspend-modifier")
            .functions()
            .first()

        // then
        sut.hasSuspendModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-open-modifier`() {
        // given
        val sut = getSnippetFile("function-has-open-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasOpenModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-override-modifier`() {
        // given
        val sut = getSnippetFile("function-has-override-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasOverrideModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-final-modifier`() {
        // given
        val sut = getSnippetFile("function-has-final-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasFinalModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-abstract-modifier`() {
        // given
        val sut = getSnippetFile("function-has-abstract-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasAbstractModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("function-has-actual-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("function-has-expect-modifier")
            .functions()
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunctiondeclaration/snippet/forkomodifierprovider/", fileName)
}
