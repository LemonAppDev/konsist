package com.lemonappdev.konsist.core.declaration.kointerfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoPathProviderTest {
    @Test
    fun `interface-file-path`() {
        // given
        val sut = getSnippetFile("interface-file-path")
            .interfaces()
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kointerfacedeclaration/snippet/forkopathprovider/interface-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `interface-project-file-path`() {
        // given
        val sut = getSnippetFile("interface-project-file-path")
            .interfaces()
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kointerfacedeclaration/snippet/" +
                    "forkopathprovider/interface-project-file-path.kt",
            )
    }

    @Test
    fun `interface-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("interface-reside-in-file-path")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration/snippet..", true) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration..interface-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("kointerfacedeclaration/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `interface-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("interface-reside-in-project-file-path")
            .interfaces()
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration/snippet..", false) shouldBeEqualTo true
            resideInPath("..kointerfacedeclaration..interface-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("kointerfacedeclaration/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerfacedeclaration/snippet/forkopathprovider/", fileName)
}
