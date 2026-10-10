package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoPathProviderTest {
    @Test
    fun `annotation-file-path`() {
        // given
        val sut =
            getSnippetFile("annotation-file-path")
                .functions()
                .first()
                .annotations
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koannotation/snippet/forkopathprovider/annotation-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `annotation-project-file-path`() {
        // given
        val sut =
            getSnippetFile("annotation-project-file-path")
                .functions()
                .first()
                .annotations
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koannotation/snippet/" +
                    "forkopathprovider/annotation-project-file-path.kt",
            )
    }

    @Test
    fun `annotation-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("annotation-reside-in-file-path")
                .functions()
                .first()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koannotation/snippet..", true) shouldBeEqualTo true
            resideInPath("..koannotation..annotation-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koannotation/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `annotation-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("annotation-reside-in-project-file-path")
                .functions()
                .first()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koannotation/snippet..", false) shouldBeEqualTo true
            resideInPath("..koannotation..annotation-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koannotation/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/forkopathprovider/", fileName)
}
