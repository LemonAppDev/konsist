package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoPathProviderTest {
    @Test
    fun `parent-file-path`() {
        // given
        val sut = getSnippetFile("parent-file-path")
            .classes()
            .parents
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koparent/snippet/forkopathprovider/parent-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `parent-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-project-file-path")
            .classes()
            .parents
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
                    "forkopathprovider/parent-project-file-path.kt",
            )
    }

    @Test
    fun `parent-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("parent-reside-in-file-path")
            .classes()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent..parent-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `parent-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-reside-in-project-file-path")
            .classes()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent..parent-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkopathprovider/", fileName)
}
