package com.lemonappdev.konsist.core.declaration.koparent

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.parents
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentDeclarationForKoPathProviderTest {
    @Test
    fun `parent-of-class-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-class-file-path")
            .classes()
            .parents
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koparent/snippet/forkopathprovider/parent-of-class-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `parent-of-class-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-class-project-file-path")
            .classes()
            .parents
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
                    "forkopathprovider/parent-of-class-project-file-path.kt",
            )
    }

    @Test
    fun `parent-of-class-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-class-reside-in-file-path")
            .classes()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent..parent-of-class-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `parent-of-class-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-class-reside-in-project-file-path")
            .classes()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent..parent-of-class-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `parent-of-interface-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-interface-file-path")
            .interfaces()
            .parents
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koparent/snippet/forkopathprovider/parent-of-interface-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `parent-of-interface-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-interface-project-file-path")
            .interfaces()
            .parents
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
                    "forkopathprovider/parent-of-interface-project-file-path.kt",
            )
    }

    @Test
    fun `parent-of-interface-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-interface-reside-in-file-path")
            .interfaces()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent..parent-of-interface-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `parent-of-interface-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-interface-reside-in-project-file-path")
            .interfaces()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent..parent-of-interface-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `parent-of-object-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-object-file-path")
            .objects()
            .parents
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koparent/snippet/forkopathprovider/parent-of-object-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `parent-of-object-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-object-project-file-path")
            .objects()
            .parents
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparent/snippet/" +
                    "forkopathprovider/parent-of-object-project-file-path.kt",
            )
    }

    @Test
    fun `parent-of-object-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-object-reside-in-file-path")
            .objects()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", true) shouldBeEqualTo true
            resideInPath("..koparent..parent-of-object-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koparent/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `parent-of-object-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("parent-of-object-reside-in-project-file-path")
            .objects()
            .parents
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent/snippet..", false) shouldBeEqualTo true
            resideInPath("..koparent..parent-of-object-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koparent/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koparent/snippet/forkopathprovider/", fileName)
}
