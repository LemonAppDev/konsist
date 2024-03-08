package com.lemonappdev.konsist.core.declaration.koobject.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `object-without-visibility-modifiers`() {
        // given
        val sut =
            getSnippetFile("object-without-visibility-modifiers")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            hasPublicModifier shouldBeEqualTo false
            hasPublicOrDefaultModifier shouldBeEqualTo true
            hasPrivateModifier shouldBeEqualTo false
            hasProtectedModifier shouldBeEqualTo false
            hasInternalModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-public-modifier`() {
        // given
        val sut =
            getSnippetFile("object-has-public-modifier")
                .objects(includeNested = true)
                .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `object-is-public-by-default`() {
        // given
        val sut =
            getSnippetFile("object-is-public-by-default")
                .objects(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            hasPublicOrDefaultModifier shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `object-has-private-modifier`() {
        // given
        val sut =
            getSnippetFile("object-has-private-modifier")
                .objects(includeNested = true)
                .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `object-has-protected-modifier`() {
        // given
        val sut =
            getSnippetFile("object-has-protected-modifier")
                .objects(includeNested = true)
                .first()

        // then
        sut.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `object-has-internal-modifier`() {
        // given
        val sut =
            getSnippetFile("object-has-internal-modifier")
                .objects(includeNested = true)
                .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/forkomodifier/snippet/forkovisibilitymodifierprovider/", fileName)
}
