package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoTypeAliasDeclarationForKoModifierProviderTest {
    @Test
    fun `typealias-without-modifiers`() {
        // given
        val sut = getSnippetFile("typealias-without-modifiers")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
            numModifiers shouldBeEqualTo 0
            hasModifiers() shouldBeEqualTo false
            hasModifiers(KoModifier.OPEN) shouldBeEqualTo false
            hasModifiers(KoModifier.OPEN, KoModifier.DATA) shouldBeEqualTo false
            hasPublicModifier shouldBeEqualTo false
            isPublicOrDefault shouldBeEqualTo true
            hasPrivateModifier shouldBeEqualTo false
            hasProtectedModifier shouldBeEqualTo false
            hasInternalModifier shouldBeEqualTo false
            hasEnumModifier shouldBeEqualTo false
            hasSealedModifier shouldBeEqualTo false
            hasInnerModifier shouldBeEqualTo false
            hasValueModifier shouldBeEqualTo false
            hasAnnotationModifier shouldBeEqualTo false
            hasDataModifier shouldBeEqualTo false
            hasActualModifier shouldBeEqualTo false
            hasExpectModifier shouldBeEqualTo false
            hasAbstractModifier shouldBeEqualTo false
            hasOpenModifier shouldBeEqualTo false
            hasFinalModifier shouldBeEqualTo false
            hasVarargModifier shouldBeEqualTo false
            hasNoInlineModifier shouldBeEqualTo false
            hasCrossInlineModifier shouldBeEqualTo false
            hasOperatorModifier shouldBeEqualTo false
            hasInlineModifier shouldBeEqualTo false
            hasTailrecModifier shouldBeEqualTo false
            hasInfixModifier shouldBeEqualTo false
            hasExternalModifier shouldBeEqualTo false
            hasSuspendModifier shouldBeEqualTo false
            hasOverrideModifier shouldBeEqualTo false
            hasFunModifier shouldBeEqualTo false
            hasLateinitModifier shouldBeEqualTo false
            hasConstModifier shouldBeEqualTo false
            hasCompanionModifier shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `typealias-modifiers`(
        fileName: String,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo listOf(PRIVATE)
            numModifiers shouldBeEqualTo 1
        }
    }

    @Test
    fun `typealias-has-public-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-public-modifier")
            .typeAliases
            .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `typealias-is-public-by-default`() {
        // given
        val sut = getSnippetFile("typealias-is-public-by-default")
            .typeAliases
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-has-private-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-private-modifier")
            .typeAliases
            .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-internal-modifier")
            .typeAliases
            .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    @Test
    fun `typealias-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("typealias-has-actual-modifier")
            .files
            .first()
            .typeAliases
            .first()

        // then
        sut.hasActualModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("typealias-has-modifiers-and-annotation-with-parameter"),
            arguments("typealias-has-modifiers-and-annotation-without-parameter"),
            arguments("typealias-has-modifiers-annotation-and-comment"),
            arguments("typealias-has-modifiers-and-annotations"),
            arguments("typealias-has-modifiers-and-annotation-with-angle-brackets"),
        )
    }
}
