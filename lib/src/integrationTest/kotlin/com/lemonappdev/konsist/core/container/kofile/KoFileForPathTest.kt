package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toOsSeparator
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForPathTest {
    @Test
    fun `file-path`() {
        // given
        val sut = getSnippetFile("file-path")
            .files()
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kofile/snippet/forpath/file-path.kt".toOsSeparator()) shouldBeEqualTo true
        }
    }

    @Test
    fun `file-project-path`() {
        // given
        val sut = getSnippetFile("file-project-path")
            .files()
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/container/kofile/snippet/forpath/" +
                    "file-project-path.kt"
                        .toOsSeparator(),
            )
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forpath/", fileName)
}
