package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.helper.ext.toOsSeparator
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoPathProviderTest {
    @Test
    fun `class-file-path`() {
        // given
        val sut =
            getSnippetFile("class-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koclass/snippet/forkopathprovider/class-file-path.kt".toOsSeparator()) shouldBeEqualTo true
        }
    }

    @Test
    fun `class-project-file-path`() {
        // given
        val sut =
            getSnippetFile("class-project-file-path")
                .classes()
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                (
                    "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koclass/snippet/" +
                        "forkopathprovider/class-project-file-path.kt"
                ).toOsSeparator(),
            )
    }

    @Test
    fun `class-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("class-reside-in-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            residesInPath("..snippet..", true) shouldBeEqualTo true
            residesInPath("..koclass/snippet..", true) shouldBeEqualTo true
            residesInPath("..koclass..class-reside-in-file-path.kt", true) shouldBeEqualTo true
            residesInPath("koclass/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `class-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("class-reside-in-project-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            residesInPath("..snippet..", false) shouldBeEqualTo true
            residesInPath("..koclass/snippet..", false) shouldBeEqualTo true
            residesInPath("..koclass..class-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            residesInPath("koclass/snippet/", false) shouldBeEqualTo false
        }
    }

    @Suppress("DEPRECATION")
    @Test
    fun `deprecated resideInPath delegates to residesInPath`() {
        // given
        val sut =
            getSnippetFile("class-reside-in-project-file-path")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..koclass/snippet..") shouldBeEqualTo true
            resideInPath("koclass/snippet/") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkopathprovider/", fileName)
}
