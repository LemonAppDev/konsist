package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.kDocs
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForKoLocationProviderTest {
    @Test
    fun `kdoc-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("kdoc-location-with-single-digit")
                .classes()
                .kDocs
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:1"
    }

    @Test
    fun `kdoc-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("kdoc-location-with-text")
                .classes()
                .kDocs
                .first()
                .projectPath

        val sut =
            getSnippetFile("kdoc-location-with-text")
                .classes()
                .kDocs
                .first()

        // then
        val declaration = "Declaration:\nThis is a sample class that demonstrates the usage of KDoc."
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdoc/snippet/forkolocationprovider/", fileName)
}
