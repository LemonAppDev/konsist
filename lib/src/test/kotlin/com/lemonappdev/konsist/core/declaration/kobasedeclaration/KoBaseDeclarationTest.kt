package com.lemonappdev.konsist.core.declaration.kobasedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.TestSnippetProvider.modulePath
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoBaseDeclarationTest {
    @Test
    fun `file-path`() {
        // given
        val sut = getSnippetFile("file-path")
            .functions()
            .first()

        // then
        assertSoftly(sut.filePath) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kobasedeclaration/snippet/file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `reside-in-file-path`() {
        // given
        val sut = getSnippetFile("reside-in-file-path")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            resideInFilePath("..snippet..") shouldBeEqualTo true
            resideInFilePath("..kobasedeclaration/snippet..") shouldBeEqualTo true
            resideInFilePath("..kobasedeclaration..reside-in-file-path.kt") shouldBeEqualTo true
            resideInFilePath("kobasedeclaration/snippet/") shouldBeEqualTo false
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
            .shouldBeEqualTo("/lib/src/test/kotlin/com/lemonappdev/konsist/core/declaration/kobasedeclaration/snippet/project-file-path.kt")
    }

    @Test
    fun `reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("reside-in-project-file-path")
            .functions()
            .first()

        // then
        assertSoftly(sut) {
            resideInProjectFilePath("..snippet..") shouldBeEqualTo true
            resideInProjectFilePath("..kobasedeclaration/snippet..") shouldBeEqualTo true
            resideInProjectFilePath("..kobasedeclaration..reside-in-project-file-path.kt") shouldBeEqualTo true
            resideInProjectFilePath("kobasedeclaration/snippet/") shouldBeEqualTo false
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
        val expected = """ 
                fun sampleFunction() {
                    "SampleText"
                }
        """.trimIndent()

        sut
            .text
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `to-string`() {
        // given
        val sut = getSnippetFile("to-string")
            .functions()
            .first()

        // then
        val expected = """
            [KoFunction] $modulePath/src/test/kotlin/com/lemonappdev/konsist/core/declaration/kobasedeclaration/snippet/to-string.kt:1:1
            fun sampleFunction() {
            }
        """.trimIndent()
        sut.toString() shouldBeEqualTo expected
    }

    @Test
    fun `class-with-parent-declaration`() {
        // given
        val sut = getSnippetFile("class-with-parent-declaration")
            .classes(includeNested = true, includeLocal = true)
            .last()

        // then
        assertSoftly(sut) {
            parentDeclaration shouldNotBeEqualTo null
            parentDeclaration?.name shouldBeEqualTo "SampleClass"
            parentDeclaration?.parentDeclaration?.name shouldBeEqualTo "SampleTopLevelInterface"
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kobasedeclaration/snippet/", fileName)
}
