package com.lemonappdev.konsist.core.declaration.koclassdeclaration.forkomodifierprovider

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `class-without-visibility-modifiers`() {
        // given
        val sut = getSnippetFile("class-without-visibility-modifiers")
            .classes()
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
    fun `public-class`() {
        // given
        val sut = getSnippetFile("public-class")
            .classes()
            .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `public-by-default-class`() {
        // given
        val sut = getSnippetFile("public-by-default-class")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            isPublicOrDefault shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `private-class`() {
        // given
        val sut = getSnippetFile("private-class")
            .classes()
            .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `protected-class`() {
        // given
        val sut = getSnippetFile("protected-class")
            .classes()
            .flatMap { it.classes() }
            .first()

        // then
        sut.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `internal-class`() {
        // given
        val sut = getSnippetFile("internal-class")
            .classes()
            .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/forkomodifierprovider/snippet/forkovisibilitymodifierprovider/", fileName)
}
