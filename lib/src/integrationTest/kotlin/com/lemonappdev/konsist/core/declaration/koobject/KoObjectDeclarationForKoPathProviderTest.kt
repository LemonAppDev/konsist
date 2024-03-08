package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoPathProviderTest {
    @Test
    fun `object-file-path`() {
        // given
        val sut =
            getSnippetFile("object-file-path")
                .objects()
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koobject/snippet/forkopathprovider/object-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `object-project-file-path`() {
        // given
        val sut =
            getSnippetFile("object-project-file-path")
                .objects()
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koobject/snippet/" +
                    "forkopathprovider/object-project-file-path.kt",
            )
    }

    @Test
    fun `object-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("object-reside-in-file-path")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koobject/snippet..", true) shouldBeEqualTo true
            resideInPath("..koobject..object-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koobject/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `object-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("object-reside-in-project-file-path")
                .objects()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koobject/snippet..", false) shouldBeEqualTo true
            resideInPath("..koobject..object-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koobject/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkopathprovider/", fileName)
}
