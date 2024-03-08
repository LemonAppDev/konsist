package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoPathProviderTest {
    @Test
    fun `init-block-file-path`() {
        // given
        val sut =
            getSnippetFile("init-block-file-path")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly {
            sut.path.startsWith("//") shouldBeEqualTo false
            sut.path.endsWith("koinitblock/snippet/forkopathprovider/init-block-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `init-block-project-file-path`() {
        // given
        val sut =
            getSnippetFile("init-block-project-file-path")
                .classes()
                .initBlocks
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koinitblock/snippet/" +
                    "forkopathprovider/init-block-project-file-path.kt",
            )
    }

    @Test
    fun `init-block-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("init-block-reside-in-file-path")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly {
            sut.resideInPath("..snippet..", true) shouldBeEqualTo true
            sut.resideInPath("..koinitblock/snippet..", true) shouldBeEqualTo true
            sut.resideInPath("..koinitblock..init-block-reside-in-file-path.kt", true) shouldBeEqualTo true
            sut.resideInPath("koinitblock/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `init-block-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("init-block-reside-in-project-file-path")
                .classes()
                .initBlocks
                .first()

        // then
        assertSoftly {
            sut.resideInPath("..snippet..", false) shouldBeEqualTo true
            sut.resideInPath("..koinitblock/snippet..", false) shouldBeEqualTo true
            sut.resideInPath("..koinitblock..init-block-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            sut.resideInPath("koinitblock/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koinitblock/snippet/forkopathprovider/", fileName)
}
