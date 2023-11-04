package com.lemonappdev.konsist.core.declaration.kogetter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `getter-without-visibility-modifiers`() {
        // given
        val sut =
            getSnippetFile("getter-without-visibility-modifiers")
                .properties()
                .first()
                .getter

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
    fun `getter-has-public-modifier`() {
        // given
        val sut =
            getSnippetFile("getter-has-public-modifier")
                .properties()
                .first()
                .getter

        // then
        sut?.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `getter-is-public-by-default`() {
        // given
        val sut =
            getSnippetFile("getter-is-public-by-default")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut) {
            it?.hasPublicOrDefaultModifier shouldBeEqualTo true
            it?.hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `getter-has-private-modifier`() {
        // given
        val sut =
            getSnippetFile("getter-has-private-modifier")
                .properties()
                .first()
                .getter

        // then
        sut?.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `getter-has-internal-modifier`() {
        // given
        val sut =
            getSnippetFile("getter-has-internal-modifier")
                .properties()
                .first()
                .getter

        // then
        sut?.hasInternalModifier shouldBeEqualTo true
    }

    @Test
    fun `getter-has-protected-modifier`() {
        // given
        val sut =
            getSnippetFile("getter-has-protected-modifier")
                .properties()
                .first()
                .getter

        // then
        sut?.hasProtectedModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kogetter/forkomodifier/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}
