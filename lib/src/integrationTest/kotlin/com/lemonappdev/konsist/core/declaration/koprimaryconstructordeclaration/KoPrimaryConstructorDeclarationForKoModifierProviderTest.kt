package com.lemonappdev.konsist.core.declaration.koprimaryconstructordeclaration

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

class KoPrimaryConstructorDeclarationForKoModifierProviderTest {
    @Test
    fun `primary-constructor-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-no-modifiers")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.modifiers?.toList() shouldBeEqualTo emptyList()
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifiers(OPEN) shouldBeEqualTo false
            it?.hasModifiers(OPEN, DATA) shouldBeEqualTo false
            it?.hasPublicModifier shouldBeEqualTo false
            it?.isPublicOrDefault shouldBeEqualTo true
            it?.hasPrivateModifier shouldBeEqualTo false
            it?.hasProtectedModifier shouldBeEqualTo false
            it?.hasInternalModifier shouldBeEqualTo false
            it?.hasEnumModifier shouldBeEqualTo false
            it?.hasSealedModifier shouldBeEqualTo false
            it?.hasInnerModifier shouldBeEqualTo false
            it?.hasValueModifier shouldBeEqualTo false
            it?.hasAnnotationModifier shouldBeEqualTo false
            it?.hasDataModifier shouldBeEqualTo false
            it?.hasActualModifier shouldBeEqualTo false
            it?.hasExpectModifier shouldBeEqualTo false
            it?.hasAbstractModifier shouldBeEqualTo false
            it?.hasOpenModifier shouldBeEqualTo false
            it?.hasFinalModifier shouldBeEqualTo false
            it?.hasVarargModifier shouldBeEqualTo false
            it?.hasNoInlineModifier shouldBeEqualTo false
            it?.hasCrossInlineModifier shouldBeEqualTo false
            it?.hasOperatorModifier shouldBeEqualTo false
            it?.hasInlineModifier shouldBeEqualTo false
            it?.hasTailrecModifier shouldBeEqualTo false
            it?.hasInfixModifier shouldBeEqualTo false
            it?.hasExternalModifier shouldBeEqualTo false
            it?.hasSuspendModifier shouldBeEqualTo false
            it?.hasOverrideModifier shouldBeEqualTo false
            it?.hasFunModifier shouldBeEqualTo false
            it?.hasLateinitModifier shouldBeEqualTo false
            it?.hasConstModifier shouldBeEqualTo false
            it?.hasCompanionModifier shouldBeEqualTo false
        }
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `primary-constructor-modifiers`(fileName: String) {
        // given
        val sut = getSnippetFile(fileName)
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.modifiers?.toList() shouldBeEqualTo listOf(PRIVATE)
    }

    @Test
    fun `primary-constructor-has-public-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-public-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-is-public-by-default`() {
        // given
        val sut = getSnippetFile("primary-constructor-is-public-by-default")
            .classes()
            .first()
            .primaryConstructor

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault shouldBeEqualTo true
            it?.hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `primary-constructor-has-private-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-private-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-protected-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `primary-constructor-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("primary-constructor-has-internal-modifier")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut?.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forkomodifierprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments("primary-constructor-has-modifier"),
            arguments("primary-constructor-has-modifiers-and-annotation-with-parameter"),
            arguments("primary-constructor-has-modifiers-and-annotation-without-parameter"),
            arguments("primary-constructor-has-modifiers-and-annotations"),
        )
    }
}
