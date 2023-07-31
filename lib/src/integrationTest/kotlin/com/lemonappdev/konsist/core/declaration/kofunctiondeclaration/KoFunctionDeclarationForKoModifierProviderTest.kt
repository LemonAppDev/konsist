package com.lemonappdev.konsist.core.declaration.kofunctiondeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.SUSPEND
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

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
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, KoModifier.DATA) shouldBeEqualTo false
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
    fun `function-has-protected-and-suspend-modifiers`() {
        // given
        val sut = getSnippetFile("function-has-protected-and-suspend-modifiers")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            numModifiers shouldBeEqualTo 2
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo true
            hasModifiers(SUSPEND) shouldBeEqualTo true
            hasModifiers(OPEN) shouldBeEqualTo false
            hasModifiers(PROTECTED, SUSPEND) shouldBeEqualTo true
            hasModifiers(SUSPEND, PROTECTED) shouldBeEqualTo true
            hasModifiers(PROTECTED, OPEN) shouldBeEqualTo false
            hasModifiers(OPEN, SUSPEND, PROTECTED) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `function-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .functions(includeNested = true)
            .first()

        // then
        sut.modifiers.toList() shouldBeEqualTo modifiers
    }

    @Test
    fun `function-has-public-modifier`() {
        // given
        val sut = getSnippetFile("function-has-public-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasPublicModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-is-public-by-default`() {
        // given
        val sut = getSnippetFile("function-is-public-by-default")
            .functions(includeNested = true)
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-private-modifier`() {
        // given
        val sut = getSnippetFile("function-has-private-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasPrivateModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("function-has-protected-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasProtectedModifier() shouldBeEqualTo true
    }

    @Test
    fun `function-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("function-has-internal-modifier")
            .functions(includeNested = true)
            .first()

        // then
        sut.hasInternalModifier() shouldBeEqualTo true
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

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("function-has-modifiers", listOf(PROTECTED, OPEN, SUSPEND, KoModifier.INLINE, KoModifier.OPERATOR)),
            arguments("function-has-modifiers-and-annotation-with-parameter", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotation-without-parameter", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-annotation-and-comment", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotations", listOf(PROTECTED, OPEN)),
            arguments("function-has-modifiers-and-annotation-with-angle-brackets", listOf(PROTECTED, OPEN)),
        )
    }
}
