package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoPathProviderTest {
    @Test
    fun `getter-file-path`() {
        // given
        val sut =
            getSnippetFile("getter-file-path")
                .properties()
                .first()
                .getter

        // then
        assertSoftly {
            sut?.path?.startsWith("//") shouldBeEqualTo false
            sut?.path?.endsWith("kogetter/snippet/forkopathprovider/getter-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `getter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("getter-project-file-path")
                .properties()
                .first()
                .getter

        // then
        sut
            ?.projectPath
            ?.shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kogetter/snippet/" +
                    "forkopathprovider/getter-project-file-path.kt",
            )
    }

    @Test
    fun `getter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("getter-reside-in-file-path")
                .properties()
                .first()
                .getter

        // then
        assertSoftly {
            sut?.resideInPath("..snippet..", true) shouldBeEqualTo true
            sut?.resideInPath("..kogetter/snippet..", true) shouldBeEqualTo true
            sut?.resideInPath("..kogetter..getter-reside-in-file-path.kt", true) shouldBeEqualTo true
            sut?.resideInPath("kogetter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `getter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("getter-reside-in-project-file-path")
                .properties()
                .first()
                .getter

        // then
        assertSoftly {
            sut?.resideInPath("..snippet..", false) shouldBeEqualTo true
            sut?.resideInPath("..kogetter/snippet..", false) shouldBeEqualTo true
            sut?.resideInPath("..kogetter..getter-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            sut?.resideInPath("kogetter/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kogetter/snippet/forkopathprovider/", fileName)
}
