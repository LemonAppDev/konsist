package com.lemonappdev.konsist.core.declaration.koparameterdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.KoModifier.DATA
import com.lemonappdev.konsist.api.KoModifier.OPEN
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

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
            it?.modifiers shouldBeEqualTo emptyList()
            it?.numModifiers shouldBeEqualTo 0
            it?.hasModifiers() shouldBeEqualTo false
            it?.hasModifiers(OPEN) shouldBeEqualTo false
            it?.hasModifiers(OPEN, DATA) shouldBeEqualTo false
            it?.hasPublicModifier shouldBeEqualTo false
            it?.isPublicOrDefault shouldBeEqualTo true
            it?.hasPrivateModifier shouldBeEqualTo false
            it?.hasProtectedModifier shouldBeEqualTo false
            it?.hasInternalModifier shouldBeEqualTo false
            it?.hasNoInlineModifier shouldBeEqualTo false
            it?.hasCrossInlineModifier shouldBeEqualTo false
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
        sut?.hasPublicModifier shouldBeEqualTo true
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
            it?.isPublicOrDefault shouldBeEqualTo true
            it?.hasPublicModifier shouldBeEqualTo false
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
        sut?.hasPrivateModifier shouldBeEqualTo true
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
        sut?.hasProtectedModifier shouldBeEqualTo true
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
        sut?.hasInternalModifier shouldBeEqualTo true
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
        sut?.hasVarArgModifier shouldBeEqualTo true
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
        sut.hasNoInlineModifier shouldBeEqualTo true
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
        sut.hasCrossInlineModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparameterdeclaration/snippet/forkomodifierprovider/", fileName)
}
