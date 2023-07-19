package com.lemonappdev.konsist.core.declaration.kopsideclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationForLocationAndTextTest {
    @Test
    fun `location-with-text`() {
        // given
        val projectPath = getSnippetFile("location-with-text")
            .declarations()
            .first()
            .projectPath

        val sut = getSnippetFile("location-with-text")
            .functions()
            .first()

        // then
        val declaration = "Declaration:\nfun sampleFunction() {\n}"
        assertSoftly(sut.locationWithText) {
            startsWith("Location: /") shouldBeEqualTo true
            contains(projectPath) shouldBeEqualTo true
            endsWith(declaration) shouldBeEqualTo true
        }
    }

    @Test
    fun `location-with-single-digit`() {
        // given
        val sut = getSnippetFile("location-with-single-digit")
            .functions()
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:3:1"
    }

    @Test
    fun `location-with-double-digit`() {
        // given
        val sut = getSnippetFile("location-with-double-digit")
            .functions(includeNested = true)
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.path}:10:25"
    }

    @Test
    fun `text`() {
        // given
        val sut = getSnippetFile("text")
            .declarations()
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
            fun sampleFunction() {
                "SampleText"
            }
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forlocationandtext/", fileName)
}
