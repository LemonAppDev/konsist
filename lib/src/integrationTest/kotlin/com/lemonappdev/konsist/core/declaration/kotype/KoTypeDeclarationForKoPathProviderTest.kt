package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationForKoPathProviderTest {
    @Test
    fun `type-file-path`() {
        // given
        val sut = getSnippetFile("type-file-path")
            .properties()
            .first()
            .explicitType

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("kotypedeclaration/snippet/forkopathprovider/type-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `type-project-file-path`() {
        // given
        val sut = getSnippetFile("type-project-file-path")
            .properties()
            .first()
            .explicitType

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypedeclaration/snippet/" +
                    "forkopathprovider/type-project-file-path.kt",
            )
    }

    @Test
    fun `type-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("type-reside-in-file-path")
            .properties()
            .first()
            .explicitType

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypedeclaration/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..kotypedeclaration..type-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("kotypedeclaration/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `type-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("type-reside-in-project-file-path")
            .properties()
            .first()
            .explicitType

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypedeclaration/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..kotypedeclaration..type-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("kotypedeclaration/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypedeclaration/snippet/forkopathprovider/", fileName)
}
