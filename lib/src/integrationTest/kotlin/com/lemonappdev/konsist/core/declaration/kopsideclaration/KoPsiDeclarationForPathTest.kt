package com.lemonappdev.konsist.core.declaration.kopsideclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPsiDeclarationForPathTest {
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
                endsWith("kopsideclaration/snippet/forpath/file-path.kt") shouldBeEqualTo true
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
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kopsideclaration/snippet/forpath/" +
                    "project-file-path.kt",
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kopsideclaration/snippet/forpath/", fileName)
}
