package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoLocationProviderTest {
    @Test
    fun `argument-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("argument-location-with-single-digit")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:2:21"
    }

    @Test
    fun `argument-location-with-text`() {
        // given
        val projectPath = getSnippetFile("argument-location-with-text")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()
            .projectPath

        val sut = getSnippetFile("argument-location-with-text")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        val declaration = "Declaration:\nsampleParameter = 0"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkolocationprovider/", fileName)
}
