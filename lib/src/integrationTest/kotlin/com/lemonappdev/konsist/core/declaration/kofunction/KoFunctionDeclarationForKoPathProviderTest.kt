package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoPathProviderTest {
    @Test
    fun `function-file-path`() {
        // given
        val sut =
            getSnippetFile("function-file-path")
                .functions()
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kofunction/snippet/forkopathprovider/function-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `function-project-file-path`() {
        // given
        val sut =
            getSnippetFile("function-project-file-path")
                .functions()
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kofunction/snippet/" +
                    "forkopathprovider/function-project-file-path.kt",
            )
    }

    @Test
    fun `function-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("function-reside-in-file-path")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kofunction/snippet..", true) shouldBeEqualTo true
            resideInPath("..kofunction..function-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("kofunction/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `function-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("function-reside-in-project-file-path")
                .functions()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kofunction/snippet..", false) shouldBeEqualTo true
            resideInPath("..kofunction..function-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("kofunction/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkopathprovider/", fileName)
}
