package com.lemonappdev.konsist.core.declaration.koconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoPathProviderTest {
    @Test
    fun `constructor-file-path`() {
        // given
        val sut = getSnippetFile("constructor-file-path")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koconstructordeclaration/snippet/forkopathprovider/constructor-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `constructor-project-file-path`() {
        // given
        val sut = getSnippetFile("constructor-project-file-path")
            .classes()
            .first()
            .constructors
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koconstructordeclaration/snippet/" +
                    "forkopathprovider/constructor-project-file-path.kt",
            )
    }

    @Test
    fun `constructor-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("constructor-reside-in-file-path")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koconstructordeclaration/snippet..", true) shouldBeEqualTo true
            resideInPath("..koconstructordeclaration..constructor-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koconstructordeclaration/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `constructor-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("constructor-reside-in-project-file-path")
            .classes()
            .first()
            .constructors
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koconstructordeclaration/snippet..", false) shouldBeEqualTo true
            resideInPath(
                "..koconstructordeclaration..constructor-reside-in-project-file-path.kt",
                false,
            ) shouldBeEqualTo true
            resideInPath("koconstructordeclaration/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructordeclaration/snippet/forkopathprovider/", fileName)
}
