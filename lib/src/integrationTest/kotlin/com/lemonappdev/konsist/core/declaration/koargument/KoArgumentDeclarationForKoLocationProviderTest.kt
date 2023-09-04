package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoLocationProviderTest {
    @Test
    fun `argument-in-enum-const-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-location-with-single-digit")
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
    fun `argument-in-enum-const-location-with-text`() {
        // given
        val projectPath = getSnippetFile("argument-in-enum-const-location-with-text")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()
            .projectPath

        val sut = getSnippetFile("argument-in-enum-const-location-with-text")
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

    @Test
    fun `argument-in-annotation-location-with-single-digit`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-location-with-single-digit")
            .functions()
            .first()
            .annotations
            .first()
            .arguments
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:32"
    }

    @Test
    fun `argument-in-annotation-location-with-text`() {
        // given
        val projectPath = getSnippetFile("argument-in-annotation-location-with-text")
            .functions()
            .first()
            .annotations
            .first()
            .arguments
            .first()
            .projectPath

        val sut = getSnippetFile("argument-in-annotation-location-with-text")
            .functions()
            .first()
            .annotations
            .first()
            .arguments
            .first()

        // then
        val declaration = "Declaration:\nsampleParameter = \"text\""
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkolocationprovider/", fileName)
}
