package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoPathProviderTest {
    @Test
    fun `constructor-file-path`() {
        // given
        val sut =
            getSnippetFile("constructor-file-path")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koconstructor/snippet/forkopathprovider/constructor-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `constructor-project-file-path`() {
        // given
        val sut =
            getSnippetFile("constructor-project-file-path")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koconstructor/snippet/" +
                    "forkopathprovider/constructor-project-file-path.kt",
            )
    }

    @Test
    fun `constructor-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("constructor-reside-in-file-path")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koconstructor/snippet..", true) shouldBeEqualTo true
            resideInPath("..koconstructor..constructor-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koconstructor/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `constructor-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("constructor-reside-in-project-file-path")
                .classes()
                .first()
                .constructors
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koconstructor/snippet..", false) shouldBeEqualTo true
            resideInPath(
                "..koconstructor..constructor-reside-in-project-file-path.kt",
                false,
            ) shouldBeEqualTo true
            resideInPath("koconstructor/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koconstructor/snippet/forkopathprovider/", fileName)
}
