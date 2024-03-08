package com.lemonappdev.konsist.core.declaration.koimport

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationForKoLocationProviderTest {
    @Test
    fun `import-location`() {
        // given
        val sut =
            getSnippetFile("import-location")
                .imports
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `import-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("import-location-with-text")
                .imports
                .first()
                .projectPath

        val sut =
            getSnippetFile("import-location-with-text")
                .imports
                .first()

        // then
        val declaration = "Declaration:\nimport com.lemonappdev.konsist.testdata.SampleType"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koimport/snippet/forkolocationprovider/", fileName)
}
