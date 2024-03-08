package com.lemonappdev.konsist.core.declaration.kosetter.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `setter-without-visibility-modifiers`() {
        // given
        val sut =
            getSnippetFile("setter-without-visibility-modifiers")
                .properties()
                .first()
                .setter

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
    fun `setter-has-public-modifier`() {
        // given
        val sut =
            getSnippetFile("setter-has-public-modifier")
                .properties()
                .first()
                .setter

        // then
        sut?.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `setter-is-public-by-default`() {
        // given
        val sut =
            getSnippetFile("setter-is-public-by-default")
                .properties()
                .first()
                .setter

        // then
        assertSoftly(sut) {
            it?.hasPublicOrDefaultModifier shouldBeEqualTo true
            it?.hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `setter-has-private-modifier`() {
        // given
        val sut =
            getSnippetFile("setter-has-private-modifier")
                .properties()
                .first()
                .setter

        // then
        sut?.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `setter-has-internal-modifier`() {
        // given
        val sut =
            getSnippetFile("setter-has-internal-modifier")
                .properties()
                .first()
                .setter

        // then
        sut?.hasInternalModifier shouldBeEqualTo true
    }

    @Test
    fun `setter-has-protected-modifier`() {
        // given
        val sut =
            getSnippetFile("setter-has-protected-modifier")
                .properties()
                .first()
                .setter

        // then
        sut?.hasProtectedModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kosetter/forkomodifier/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}
