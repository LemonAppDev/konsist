package com.lemonappdev.konsist.core.declaration.kokdoc

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoKDocDeclarationForKoPathProviderTest {
    @Test
    fun `kdoc-file-path`() {
        // given
        val sut =
            getSnippetFile("kdoc-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kokdoc/snippet/forkopathprovider/kdoc-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `kdoc-project-file-path`() {
        // given
        val sut =
            getSnippetFile("kdoc-project-file-path")
                .classes()
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kokdoc/snippet/" +
                    "forkopathprovider/kdoc-project-file-path.kt",
            )
    }

    @Test
    fun `kdoc-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("kdoc-reside-in-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kokdoc/snippet..", true) shouldBeEqualTo true
            resideInPath("..kokdoc..kdoc-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("kokdoc/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `kdoc-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("kdoc-reside-in-project-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kokdoc/snippet..", false) shouldBeEqualTo true
            resideInPath("..kokdoc..kdoc-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("kokdoc/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kokdoc/snippet/forkopathprovider/", fileName)
}
