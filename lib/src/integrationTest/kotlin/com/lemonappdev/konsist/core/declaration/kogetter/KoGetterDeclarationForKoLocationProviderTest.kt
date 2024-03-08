package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoLocationProviderTest {
    @Test
    fun `getter-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("getter-location-with-single-digit")
                .properties()
                .first()
                .getter

        // then
        sut?.location shouldBeEqualTo "${sut?.path}:2:5"
    }

    @Test
    fun `getter-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("getter-location-with-text")
                .properties()
                .first()
                .getter
                ?.projectPath

        val sut =
            getSnippetFile("getter-location-with-text")
                .properties()
                .first()
                .getter

        // then
        assertSoftly(sut?.locationWithText) {
            it?.startsWith("Location: /") shouldBeEqualTo true
            projectPath?.let { path -> it?.contains(path) } shouldBeEqualTo true
            it?.contains("get() = \"\"") shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kogetter/snippet/forkolocationprovider/", fileName)
}
