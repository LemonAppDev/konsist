package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoLocationProviderTest {
    @Test
    fun `constructor-location-with-single-digit`() {
        // given
        val sut =
            getSnippetFile("constructor-location-with-single-digit")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:18"
    }

    @Test
    fun `constructor-location-with-text`() {
        // given
        val projectPath =
            getSnippetFile("constructor-location-with-text")
                .classes()
                .first()
                .constructors
                .first()
                .projectPath

        val sut =
            getSnippetFile("constructor-location-with-text")
                .classes()
                .first()
                .constructors
                .first()

        // then
        val declaration = "Declaration:\n(val sampleParameter: SampleType)"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructor/snippet/forkolocationprovider/", fileName)
}
