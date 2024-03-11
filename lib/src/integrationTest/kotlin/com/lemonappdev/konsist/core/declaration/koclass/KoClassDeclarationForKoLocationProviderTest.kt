package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoLocationProviderTest {
    @Test
    fun `class-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("class-location-with-single-digit")
                .classes()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:1"
    }

    @Test
    fun `class-location-with-double-digit`() {
        // given
        val sut =
            getSnippetFile("class-location-with-double-digit")
                .classes(includeNested = true)
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:10:37"
    }

    @Test
    fun `class-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("class-location-with-text")
                .classes()
                .first()
                .projectPath

        val sut =
            getSnippetFile("class-location-with-text")
                .classes()
                .first()

        // then
        val declaration = "Declaration:\nclass SampleClass {\n}"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkolocationprovider/", fileName)
}
