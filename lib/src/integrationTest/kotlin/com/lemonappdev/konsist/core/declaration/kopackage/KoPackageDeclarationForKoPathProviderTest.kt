package com.lemonappdev.konsist.core.declaration.kopackage

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPackageDeclarationForKoPathProviderTest {
    @Test
    fun `package-file-path`() {
        // given
        val sut =
            getSnippetFile("package-file-path")
                .packages
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kopackage/snippet/forkopathprovider/package-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `package-project-file-path`() {
        // given
        val sut =
            getSnippetFile("package-project-file-path")
                .packages
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kopackage/snippet/" +
                    "forkopathprovider/package-project-file-path.kt",
            )
    }

    @Test
    fun `package-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("package-reside-in-file-path")
                .packages
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kopackage/snippet..", true) shouldBeEqualTo true
            resideInPath("..kopackage..package-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("kopackage/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `package-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("package-reside-in-project-file-path")
                .packages
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kopackage/snippet..", false) shouldBeEqualTo true
            resideInPath("..kopackage..package-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("kopackage/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kopackage/snippet/forkopathprovider/", fileName)
}
