package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoVisibilityModifierProviderTest {
    @Test
    fun `function-without-visibility-modifiers`() {
        // given
        val sut =
            getSnippetFile("function-without-visibility-modifiers")
                .functions()
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
    fun `function-has-public-modifier`() {
        // given
        val sut =
            getSnippetFile("function-has-public-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasPublicModifier shouldBeEqualTo true
    }

    @Test
    fun `function-is-public-by-default`() {
        // given
        val sut =
            getSnippetFile("function-is-public-by-default")
                .functions(includeNested = true)
                .first()

        // then
        assertSoftly(sut) {
            hasPublicOrDefaultModifier shouldBeEqualTo true
            hasPublicModifier shouldBeEqualTo false
        }
    }

    @Test
    fun `function-has-private-modifier`() {
        // given
        val sut =
            getSnippetFile("function-has-private-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasPrivateModifier shouldBeEqualTo true
    }

    @Test
    fun `function-has-protected-modifier`() {
        // given
        val sut =
            getSnippetFile("function-has-protected-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasProtectedModifier shouldBeEqualTo true
    }

    @Test
    fun `function-has-internal-modifier`() {
        // given
        val sut =
            getSnippetFile("function-has-internal-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasInternalModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkovisibilitymodifierprovider/", fileName)
}
