package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoLocationProviderTest {
    @Test
    fun `annotation-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("annotation-location-with-single-digit")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:1"
    }

    @Test
    fun `annotation-location-with-double-digit`() {
        // given
        val sut =
            getSnippetFile("annotation-location-with-double-digit")
                .functions(includeNested = true)
                .first()
                .annotations
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:10:29"
    }

    @Test
    fun `annotation-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("annotation-location-with-text")
                .functions()
                .first()
                .annotations
                .first()
                .projectPath

        val sut =
            getSnippetFile("annotation-location-with-text")
                .functions()
                .first()
                .annotations
                .first()

        // then
        val declaration = "Declaration:\n@SampleAnnotation"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koannotation/snippet/forkolocationprovider/", fileName)
}
