package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoPathProviderTest {
    @Test
    fun `enum-const-file-path`() {
        // given
        val sut =
            getSnippetFile("enum-const-file-path")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koenumconstant/snippet/forkopathprovider/enum-const-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `enum-const-project-file-path`() {
        // given
        val sut =
            getSnippetFile("enum-const-project-file-path")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koenumconstant/snippet/" +
                    "forkopathprovider/enum-const-project-file-path.kt",
            )
    }

    @Test
    fun `enum-const-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("enum-const-reside-in-file-path")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koenumconstant/snippet..", true) shouldBeEqualTo true
            resideInPath("..koenumconstant..enum-const-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koenumconstant/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `enum-const-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("enum-const-reside-in-project-file-path")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koenumconstant/snippet..", false) shouldBeEqualTo true
            resideInPath("..koenumconstant..enum-const-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koenumconstant/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koenumconstant/snippet/forkopathprovider/", fileName)
}
