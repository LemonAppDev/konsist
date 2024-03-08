package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoLocationProviderTest {
    @Test
    fun `property-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("property-location-with-single-digit")
                .properties()
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:1:1"
    }

    @Test
    fun `property-location-with-double-digit`() {
        // given
        val sut =
            getSnippetFile("property-location-with-double-digit")
                .properties(includeNested = true)
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:10:37"
    }

    @Test
    fun `property-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("property-location-with-text")
                .properties()
                .first()
                .projectPath

        val sut =
            getSnippetFile("property-location-with-text")
                .properties()
                .first()

        // then
        val declaration = "Declaration:\nval sampleProperty = \"\""
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkolocationprovider/", fileName)
}
