package com.lemonappdev.konsist.core.declaration.koconstructordeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `constructor-has-no-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("constructor-has-no-visibility-modifiers")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            hasPublicModifier shouldBeEqualTo false
            isPublicOrDefault shouldBeEqualTo true
            hasPrivateModifier shouldBeEqualTo false
            hasProtectedModifier shouldBeEqualTo false
            hasInternalModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `constructor-has-public-modifier`() {
        // given
        val sut = getSnippetFile("constructor-has-public-modifier")
            .classes()
            .first()
            .constructors
            .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `constructor-is-public-by-default`() {
        // given
        val sut = getSnippetFile("constructor-is-public-by-default")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `constructor-has-private-modifier`() {
        // given
        val sut = getSnippetFile("constructor-has-private-modifier")
            .classes()
            .first()
            .constructors
            .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `constructor-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("constructor-has-protected-modifier")
            .classes()
            .first()
            .constructors
            .first()

        // then
        sut.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `constructor-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("constructor-has-internal-modifier")
            .classes()
            .first()
            .constructors
            .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/koconstructordeclaration/forkomodifierprovider/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}
