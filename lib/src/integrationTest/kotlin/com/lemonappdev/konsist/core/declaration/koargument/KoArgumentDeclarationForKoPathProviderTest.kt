package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.annotations
import com.lemonappdev.konsist.api.ext.list.provider.arguments
import com.lemonappdev.konsist.api.ext.list.provider.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoPathProviderTest {
    @Test
    fun `argument-in-enum-const-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-file-path")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koargument/snippet/forkopathprovider/argument-in-enum-const-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `argument-in-enum-const-project-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-project-file-path")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koargument/snippet/" +
                    "forkopathprovider/argument-in-enum-const-project-file-path.kt",
            )
    }

    @Test
    fun `argument-in-enum-const-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-reside-in-file-path")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koargument/snippet..", true) shouldBeEqualTo true
            resideInPath("..koargument..argument-in-enum-const-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-enum-const-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-reside-in-project-file-path")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koargument/snippet..", false) shouldBeEqualTo true
            resideInPath("..koargument..argument-in-enum-const-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koargument/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-annotation-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-file-path")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koargument/snippet/forkopathprovider/argument-in-annotation-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `argument-in-annotation-project-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-project-file-path")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koargument/snippet/" +
                    "forkopathprovider/argument-in-annotation-project-file-path.kt",
            )
    }

    @Test
    fun `argument-in-annotation-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-reside-in-file-path")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koargument/snippet..", true) shouldBeEqualTo true
            resideInPath("..koargument..argument-in-annotation-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koargument/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-annotation-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-reside-in-project-file-path")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koargument/snippet..", false) shouldBeEqualTo true
            resideInPath("..koargument..argument-in-annotation-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koargument/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koargument/snippet/forkopathprovider/", fileName)
}
