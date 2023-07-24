package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import com.lemonappdev.konsist.api.KoModifier.PRIVATE
import com.lemonappdev.konsist.api.KoModifier.PROTECTED
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class KoParameterDeclarationForKoModifierProviderTest {
    @Test
    fun `parameter-has-no-modifiers`() {
        // given
        val sut = getSnippetFile("parameter-has-no-modifiers")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.modifiers?.toList() shouldBeEqualTo emptyList()
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifiers(OPEN) shouldBeEqualTo false
            it?.hasModifiers(OPEN, DATA) shouldBeEqualTo false
            it?.hasPublicModifier() shouldBeEqualTo false
            it?.isPublicOrDefault() shouldBeEqualTo true
            it?.hasPrivateModifier() shouldBeEqualTo false
            it?.hasProtectedModifier() shouldBeEqualTo false
            it?.hasInternalModifier() shouldBeEqualTo false
            it?.hasEnumModifier() shouldBeEqualTo false
            it?.hasSealedModifier() shouldBeEqualTo false
            it?.hasInnerModifier() shouldBeEqualTo false
            it?.hasValueModifier() shouldBeEqualTo false
            it?.hasAnnotationModifier() shouldBeEqualTo false
            it?.hasDataModifier() shouldBeEqualTo false
            it?.hasActualModifier() shouldBeEqualTo false
            it?.hasExpectModifier() shouldBeEqualTo false
            it?.hasAbstractModifier() shouldBeEqualTo false
            it?.hasOpenModifier() shouldBeEqualTo false
            it?.hasFinalModifier() shouldBeEqualTo false
            it?.hasVarargModifier() shouldBeEqualTo false
            it?.hasNoInlineModifier() shouldBeEqualTo false
            it?.hasCrossInlineModifier() shouldBeEqualTo false
            it?.hasOperatorModifier() shouldBeEqualTo false
            it?.hasInlineModifier() shouldBeEqualTo false
            it?.hasTailrecModifier() shouldBeEqualTo false
            it?.hasInfixModifier() shouldBeEqualTo false
            it?.hasExternalModifier() shouldBeEqualTo false
            it?.hasSuspendModifier() shouldBeEqualTo false
            it?.hasOverrideModifier() shouldBeEqualTo false
            it?.hasFunModifier() shouldBeEqualTo false
            it?.hasLateinitModifier() shouldBeEqualTo false
            it?.hasConstModifier() shouldBeEqualTo false
            it?.hasCompanionModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-has-public-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-public-modifier")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.hasPublicModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-is-public-by-default`() {
        // given
        val sut = getSnippetFile("parameter-is-public-by-default")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.isPublicOrDefault() shouldBeEqualTo true
            it?.hasPublicModifier() shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-has-private-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-private-modifier")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.hasPrivateModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-protected-modifier")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.hasProtectedModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-internal-modifier")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.hasInternalModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-vararg-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-vararg-modifier")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        sut?.hasVarargModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-noinline-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-noinline-modifier")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.hasNoInlineModifier() shouldBeEqualTo true
    }

    @Test
    fun `parameter-has-crossinline-modifier`() {
        // given
        val sut = getSnippetFile("parameter-has-crossinline-modifier")
            .functions()
            .first()
            .parameters
            .first()

        // then
        sut.hasCrossInlineModifier() shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forkomodifierprovider/", fileName)
}
