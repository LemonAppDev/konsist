package com.lemonappdev.konsist.core.declaration.kopsideclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationTest {
    @Test
    fun `file-path`() {
        // given
        val sut = getSnippetFile("file-path")
            .functions()
            .first()

        // then
        sut
            .filePath
            .run {
                startsWith("//") shouldBeEqualTo false
                endsWith("kopsideclaration/snippet/file-path.kt") shouldBeEqualTo true
            }
    }

    @Test
    fun `file-path-test`() {
        // given
        val sut = getSnippetFile("file-path")
            .functions()
            .first()

        // then
        sut
            .filePath
            .run {
                startsWith("//") shouldBeEqualTo false
                endsWith("kopsideclaration/snippet/file-path.kt") shouldBeEqualTo true
            }
    }

    @Test
    fun `reside-in-file-path`() {
        // given
        val sut = getSnippetFile("reside-in-file-path")
            .functions()
            .first()

        // then
        sut.run {
            resideInFilePath("..snippet..") shouldBeEqualTo true
            resideInFilePath("..kopsideclaration/snippet..") shouldBeEqualTo true
            resideInFilePath("..kopsideclaration..reside-in-file-path.kt") shouldBeEqualTo true
            resideInFilePath("kopsideclaration/snippet/") shouldBeEqualTo false
        }
    }

    @Test
    fun `project-file-path`() {
        // given
        val sut = getSnippetFile("project-file-path")
            .files()
            .first()

        // then
        sut
            .projectFilePath
            .shouldBeEqualTo("/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kopsideclaration/snippet/project-file-path.kt")
    }

    @Test
    fun `reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("reside-in-project-file-path")
            .functions()
            .first()

        // then
        sut.run {
            resideInProjectFilePath("..snippet..") shouldBeEqualTo true
            resideInProjectFilePath("..kopsideclaration/snippet..") shouldBeEqualTo true
            resideInProjectFilePath("..kopsideclaration..reside-in-project-file-path.kt") shouldBeEqualTo true
            resideInProjectFilePath("kopsideclaration/snippet/") shouldBeEqualTo false
        }
    }

    @Test
    fun `location-with-text`() {
        // given
        val projectPath = getSnippetFile("location-with-text")
            .files()
            .first()
            .projectFilePath

        val sut = getSnippetFile("location-with-text")
            .functions()
            .first()

        // then
        val declaration = "Declaration:\nfun sampleFunction() {\n}"
        sut
            .locationWithText
            .run {
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
        sut.location shouldBeEqualTo "${sut.filePath}:3:1"
    }

    @Test
    fun `location-with-double-digit`() {
        // given
        val sut = getSnippetFile("location-with-double-digit")
            .functions(includeNested = true)
            .first()

        // then
        sut.location shouldBeEqualTo "${sut.filePath}:12:25"
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
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/", fileName)
}
