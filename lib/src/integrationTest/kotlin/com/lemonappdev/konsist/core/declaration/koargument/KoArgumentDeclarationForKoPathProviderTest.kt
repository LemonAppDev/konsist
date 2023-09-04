package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoPathProviderTest {
    @Test
    fun `argument-file-path`() {
        // given
        val sut = getSnippetFile("argument-file-path")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koargument/snippet/forkopathprovider/argument-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `argument-project-file-path`() {
        // given
        val sut = getSnippetFile("argument-project-file-path")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koargument/snippet/" +
                    "forkopathprovider/argument-project-file-path.kt",
            )
    }

    @Test
    fun `argument-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("argument-reside-in-file-path")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koargument/snippet..", true) shouldBeEqualTo true
            resideInPath("..koargument..argument-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("argument-reside-in-project-file-path")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koargument/snippet..", false) shouldBeEqualTo true
            resideInPath("..koargument..argument-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koargument/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkopathprovider/", fileName)
}
