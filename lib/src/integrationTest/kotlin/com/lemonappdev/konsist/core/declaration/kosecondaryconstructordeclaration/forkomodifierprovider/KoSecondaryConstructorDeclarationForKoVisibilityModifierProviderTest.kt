package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `secondary-constructor-has-no-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-no-visibility-modifiers")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.hasPublicModifier shouldBeEqualTo false
            it.isPublicOrDefault shouldBeEqualTo true
            it.hasPrivateModifier shouldBeEqualTo false
            it.hasProtectedModifier shouldBeEqualTo false
            it.hasInternalModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-public-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-public-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor-is-public-by-default`() {
        // given
        val sut = getSnippetFile("secondary-constructor-is-public-by-default")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        assertSoftly(sut) {
            it.isPublicOrDefault shouldBeEqualTo true
            it.hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `secondary-constructor-has-private-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-private-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-protected-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `secondary-constructor-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("secondary-constructor-has-internal-modifier")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kosecondaryconstructordeclaration/forkomodifierprovider/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}
