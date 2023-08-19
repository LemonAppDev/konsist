package com.lemonappdev.konsist.core.declaration.koparameter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `parameter-has-no-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("parameter-has-no-visibility-modifiers")
            .classes()
            .first()
            .primaryConstructor
            ?.parameters
            ?.first()

        // then
        assertSoftly(sut) {
            it?.hasPublicModifier shouldBeEqualTo false
            it?.hasPublicOrDefaultModifier shouldBeEqualTo true
            it?.hasPrivateModifier shouldBeEqualTo false
            it?.hasProtectedModifier shouldBeEqualTo false
            it?.hasInternalModifier shouldBeEqualTo false
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
            it?.hasPublicOrDefaultModifier shouldBeEqualTo true
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/koparameter/forkomodifier/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}
