package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoPathProviderTest {
    @Test
    fun `file-path`() {
        // given
        val sut =
            getSnippetFile("file-path")
                .files
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kofile/snippet/forkopathprovider/file-path.kt".toOsSeparator()) shouldBeEqualTo true
        }
    }

    @Test
    fun `file-project-path`() {
        // given
        val sut =
            getSnippetFile("file-project-path")
                .files
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                (
                    "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kofile/snippet/forkopathprovider/" +
                        "file-project-path.kt"
                ).toOsSeparator(),
            )
    }

    @Test
    fun `reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("reside-in-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            residesInPath("..snippet..", true) shouldBeEqualTo true
            residesInPath("..kofile/snippet..", true) shouldBeEqualTo true
            residesInPath("..kofile..reside-in-file-path.kt", true) shouldBeEqualTo true
            residesInPath("kofile/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("reside-in-project-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            residesInPath("..snippet..", false) shouldBeEqualTo true
            residesInPath("..kofile/snippet..", false) shouldBeEqualTo true
            residesInPath("..kofile..reside-in-project-file-path.kt", false) shouldBeEqualTo true
            residesInPath("kofile/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkopathprovider/",
            fileName,
        )
}
