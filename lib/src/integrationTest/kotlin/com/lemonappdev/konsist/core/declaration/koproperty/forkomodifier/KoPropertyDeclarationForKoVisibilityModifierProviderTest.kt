package com.lemonappdev.konsist.core.declaration.koproperty.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `property-has-no-visibility-modifiers`() {
        // given
        val sut =
            getSnippetFile("property-has-no-visibility-modifiers")
                .properties()
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
    fun `property-has-public-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-public-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `property-is-public-by-default`() {
        // given
        val sut =
            getSnippetFile("property-is-public-by-default")
                .properties(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            hasPublicOrDefaultModifier shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `property-has-private-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-private-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `property-has-protected-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-protected-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `property-has-internal-modifier`() {
        // given
        val sut =
            getSnippetFile("property-has-internal-modifier")
                .properties(includeNested = true)
                .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/forkomodifier/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}
