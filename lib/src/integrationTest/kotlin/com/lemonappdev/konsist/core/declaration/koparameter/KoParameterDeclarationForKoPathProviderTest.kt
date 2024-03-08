package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoPathProviderTest {
    @Test
    fun `parameter-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-file-path")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("koparameter/snippet/forkopathprovider/parameter-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `parameter-project-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-project-file-path")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        sut
            ?.projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparameter/snippet/" +
                    "forkopathprovider/parameter-project-file-path.kt",
            )
    }

    @Test
    fun `parameter-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-reside-in-file-path")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koparameter..parameter-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("koparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-reside-in-project-file-path")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koparameter..parameter-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("koparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkopathprovider/", fileName)
}
