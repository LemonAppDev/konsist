package com.lemonappdev.konsist.core.declaration.kobasedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationTest {
    @Test
    fun `path`() {
        // given
        val sut = getSnippetFile("path")
            .functions()
            .first()

        // then
        sut
            .path
            .run {
                startsWith("//") shouldBeEqualTo false
                endsWith("kobasedeclaration/snippet/path.kt") shouldBeEqualTo true
            }
    }

    @Test
    fun `has-file-path`() {
        // given
        val sut = getSnippetFile("has-file-path")
            .functions()
            .first()

        // then
        sut.run {
            hasFilePath("..snippet..") shouldBeEqualTo true
            hasFilePath("..kobasedeclaration/snippet..") shouldBeEqualTo true
            hasFilePath("..kobasedeclaration..has-file-path.kt") shouldBeEqualTo true
            hasFilePath("kobasedeclaration/snippet/") shouldBeEqualTo false
        }
    }

    @Test
    fun `project-path`() {
        // given
        val sut = getSnippetFile("project-path")
            .files()
            .first()

        // then
        sut.projectPath shouldBeEqualTo "/lib/src/test/kotlin/com/lemonappdev/konsist/core/declaration/kobasedeclaration/snippet/project-path.kt"
    }

    @Test
    fun `text-with-location`() {
        // given
        val projectPath = getSnippetFile("text-with-location")
            .files()
            .first()
            .projectPath

        val sut = getSnippetFile("text-with-location")
            .functions()
            .first()

        // then
        val declaration = "Declaration:\nfun sampleFunction() {\n}"
        sut
            .textWithLocation
            .run {
                startsWith("Location: /") shouldBeEqualTo true
                contains(projectPath) shouldBeEqualTo true
                endsWith(declaration) shouldBeEqualTo true
            }
    }

    @Test
    fun `containing-file`() {
        // given
        val sut = getSnippetFile("containing-file")
            .files()
            .first()

        // then
        sut
            .containingFile
            .name
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
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
        sut.location shouldBeEqualTo "${sut.path}:12:25"
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

    @Test
    fun `to-string`() {
        // given
        val sut = getSnippetFile("to-string")
            .functions()
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.textWithLocation
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kobasedeclaration/snippet/", fileName)
}
