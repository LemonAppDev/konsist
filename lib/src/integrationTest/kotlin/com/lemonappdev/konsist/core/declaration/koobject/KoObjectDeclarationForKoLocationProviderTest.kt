package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoLocationProviderTest {
    @Test
    fun `object-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("object-location-with-single-digit")
                .objects()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `object-location-with-double-digit`() {
        // given
        val sut =
            getSnippetFile("object-location-with-double-digit")
                .objects(includeNested = true)
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:10:37"
    }

    @Test
    fun `object-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("object-location-with-text")
                .objects()
                .first()
                .projectPath

        val sut =
            getSnippetFile("object-location-with-text")
                .objects()
                .first()

        // then
        val declaration = "Declaration:\nobject SampleObject {\n}"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkolocationprovider/", fileName)
}
