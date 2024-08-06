package com.lemonappdev.konsist.core.declaration.koparameter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParameterDeclarationForKoPathProviderTest {
    @Test
    fun `parameter-in-constructor-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-file-path")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut?.path) {
            it?.startsWith("//") shouldBeEqualTo false
            it?.endsWith("koparameter/snippet/forkopathprovider/parameter-in-constructor-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `parameter-in-function-invocation-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-file-path")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koparameter/snippet/forkopathprovider/parameter-in-function-invocation-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `parameter-in-constructor-project-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-project-file-path")
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
                    "forkopathprovider/parameter-in-constructor-project-file-path.kt",
            )
    }

    @Test
    fun `parameter-in-function-invocation-project-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-project-file-path")
                .functions()
                .first()
                .parameters
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koparameter/snippet/" +
                    "forkopathprovider/parameter-in-function-invocation-project-file-path.kt",
            )
    }

    @Test
    fun `parameter-in-constructor-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-reside-in-file-path")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koparameter/snippet..", true) shouldBeEqualTo true
            it?.resideInPath("..koparameter..parameter-in-constructor-reside-in-file-path.kt", true) shouldBeEqualTo true
            it?.resideInPath("koparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-function-invocation-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-reside-in-file-path")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koparameter/snippet..", true) shouldBeEqualTo true
            resideInPath("..koparameter..parameter-in-function-invocation-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koparameter/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-constructor-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-constructor-reside-in-project-file-path")
                .classes()
                .first()
                .primaryConstructor
                ?.parameters
                ?.first()

        // then
        assertSoftly(sut) {
            it?.resideInPath("..snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koparameter/snippet..", false) shouldBeEqualTo true
            it?.resideInPath("..koparameter..parameter-in-constructor-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            it?.resideInPath("koparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    @Test
    fun `parameter-in-function-invocation-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("parameter-in-function-invocation-reside-in-project-file-path")
                .functions()
                .first()
                .parameters
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koparameter/snippet..", false) shouldBeEqualTo true
            resideInPath("..koparameter..parameter-in-function-invocation-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koparameter/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koparameter/snippet/forkopathprovider/", fileName)
}
