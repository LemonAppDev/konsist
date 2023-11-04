package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoLocationProviderTest {
    @Test
    fun `function-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("function-location-with-single-digit")
                .functions()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `function-location-with-double-digit`() {
        // given
        val sut =
            getSnippetFile("function-location-with-double-digit")
                .functions(includeNested = true)
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:10:37"
    }

    @Test
    fun `function-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("function-location-with-text")
                .functions()
                .first()
                .projectPath

        val sut =
            getSnippetFile("function-location-with-text")
                .functions()
                .first()

        // then
        val declaration = "Declaration:\nfun sampleFunction() {\n}"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkolocationprovider/", fileName)
}
