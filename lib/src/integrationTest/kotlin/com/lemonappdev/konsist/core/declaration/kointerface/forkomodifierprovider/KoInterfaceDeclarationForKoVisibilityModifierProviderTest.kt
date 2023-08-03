package com.lemonappdev.konsist.core.declaration.kointerface.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `interface-has-no-visibility-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-no-visibility-modifier")
            .interfaces()
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
    fun `interface-has-public-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-public-modifier")
            .interfaces()
            .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `interface-is-public-by-default`() {
        // given
        val sut = getSnippetFile("interface-is-public-by-default")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-has-private-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-private-modifier")
            .interfaces()
            .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `interface-has-protected-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-protected-modifier")
            .classes()
            .flatMap { it.interfaces() }
            .first()

        // then
        sut.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `interface-has-internal-modifier`() {
        // given
        val sut = getSnippetFile("interface-has-internal-modifier")
            .interfaces()
            .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kointerfacedeclaration/forkomodifierprovider/snippet/forkovisibilitymodifierprovider/",
            fileName,
        )
}
