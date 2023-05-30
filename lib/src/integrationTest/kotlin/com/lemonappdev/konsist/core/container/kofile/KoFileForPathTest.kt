package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.core.ext.toNormalizedPath
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
            endsWith("kofile/snippet/forpath/file-path.kt".toNormalizedPath()) shouldBeEqualTo true
        }
    }

    @Test
    fun `file-root-project-path`() {
        // given
        val sut = getSnippetFile("file-root-project-path")
            .files()
            .first()

        // then
        sut
            .rootProjectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/container/kofile/snippet/forpath/" +
                    "file-root-project-path.kt"
                        .toNormalizedPath(),
            )
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forpath/", fileName)
}
