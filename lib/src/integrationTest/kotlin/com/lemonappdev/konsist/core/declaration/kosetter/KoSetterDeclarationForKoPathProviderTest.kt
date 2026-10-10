package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoPathProviderTest {
    @Test
    fun `setter-file-path`() {
        // given
        val sut =
            getSnippetFile("setter-file-path")
                .properties()
                .first()
                .setter

        // then
        assertSoftly {
            sut?.path?.startsWith("//") shouldBeEqualTo false
            sut?.path?.endsWith("kosetter/snippet/forkopathprovider/setter-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `setter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("setter-project-file-path")
                .properties()
                .first()
                .setter

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kosetter/snippet/" +
                    "forkopathprovider/setter-project-file-path.kt",
            )
    }

    @Test
    fun `setter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("setter-reside-in-file-path")
                .properties()
                .first()
                .setter

        // then
        assertSoftly {
            sut?.resideInPath("..snippet..", true) shouldBeEqualTo true
            sut?.resideInPath("..kosetter/snippet..", true) shouldBeEqualTo true
            sut?.resideInPath("..kosetter..setter-reside-in-file-path.kt", true) shouldBeEqualTo true
            sut?.resideInPath("kosetter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `setter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("setter-reside-in-project-file-path")
                .properties()
                .first()
                .setter

        // then
        assertSoftly {
            sut?.resideInPath("..snippet..", false) shouldBeEqualTo true
            sut?.resideInPath("..kosetter/snippet..", false) shouldBeEqualTo true
            sut?.resideInPath("..kosetter..setter-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            sut?.resideInPath("kosetter/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kosetter/snippet/forkopathprovider/", fileName)
}
