package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.ABSTRACT
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import com.lemonappdev.konsist.api.KoModifier.PUBLIC
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoInterfaceDeclarationForKoModifierProviderTest {
    @Test
    fun `interface-has-no-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-no-modifier")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            modifiers shouldBeEqualTo emptyList()
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
    fun `interface-has-public-and-abstract-modifiers`() {
        // given
        val sut = getSnippetFile("interface-has-public-and-abstract-modifiers")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            hasModifiers() shouldBeEqualTo true
            hasModifiers(PUBLIC) shouldBeEqualTo true
            hasModifiers(ABSTRACT) shouldBeEqualTo true
            hasModifiers(PROTECTED) shouldBeEqualTo false
            hasModifiers(PUBLIC, ABSTRACT) shouldBeEqualTo true
            hasModifiers(ABSTRACT, PUBLIC) shouldBeEqualTo true
            hasModifiers(PROTECTED, ABSTRACT) shouldBeEqualTo false
            hasModifiers(PROTECTED, ABSTRACT, PUBLIC) shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `interface-modifiers`(
        fileName: String,
        modifiers: List<KoModifier>,
    ) {
        // given
        val sut = getSnippetFile(fileName)
            .interfaces()
            .first()

        // then
        sut.modifiers shouldBeEqualTo modifiers
    }

    @Test
    fun `interface-has-public-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-public-modifier")
            .interfaces()
            .first()

        // then
        sut.hasPublicModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-is-public-by-default`() {
        // given
        val sut = getSnippetFile("interface-is-public-by-default")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault() shouldBeEqualTo true
            hasPublicModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-private-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-private-modifier")
            .interfaces()
            .first()

        // then
        sut.hasPrivateModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-protected-modifier")
            .classes()
            .flatMap { it.interfaces() }
            .first()

        // then
        sut.hasProtectedModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-internal-modifier")
            .interfaces()
            .first()

        // then
        sut.hasInternalModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-actual-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-actual-modifier")
            .interfaces()
            .first()

        // then
        sut.hasActualModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-expect-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-expect-modifier")
            .interfaces()
            .first()

        // then
        sut.hasExpectModifier() shouldBeEqualTo true
    }

    @Test
    fun `interface-has-fun-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-fun-modifier")
            .interfaces()
            .first()

        // then
        sut.hasFunModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("interface-has-modifiers-and-annotation-with-parameter", listOf(PUBLIC, ABSTRACT)),
            arguments("interface-has-modifiers-and-annotation-without-parameter", listOf(PUBLIC, ABSTRACT)),
            arguments("interface-has-modifiers-annotation-and-comment", listOf(PUBLIC, ABSTRACT)),
            arguments("interface-has-modifiers-and-annotations", listOf(PUBLIC, ABSTRACT)),
            arguments("interface-has-modifiers-and-annotation-with-angle-brackets", listOf(PUBLIC, ABSTRACT)),
        )
    }
}
