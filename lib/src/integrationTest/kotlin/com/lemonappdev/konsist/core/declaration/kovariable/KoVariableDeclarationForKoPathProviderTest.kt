package com.lemonappdev.konsist.core.declaration.kovariable

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.variables
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoVariableDeclarationForKoPathProviderTest {
    @Test
    fun `variable-file-path`() {
        // given
        val sut = getSnippetFile("variable-file-path")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kovariable/snippet/forkopathprovider/variable-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `variable-project-file-path`() {
        // given
        val sut = getSnippetFile("variable-project-file-path")
            .functions()
            .variables
            .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kovariable/snippet/" +
                    "forkopathprovider/variable-project-file-path.kt",
            )
    }

    @Test
    fun `variable-reside-in-file-path`() {
        // given
        val sut = getSnippetFile("variable-reside-in-file-path")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kovariable/snippet..", true) shouldBeEqualTo true
            resideInPath("..kovariable..variable-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("kovariable/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `variable-reside-in-project-file-path`() {
        // given
        val sut = getSnippetFile("variable-reside-in-project-file-path")
            .functions()
            .variables
            .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kovariable/snippet..", false) shouldBeEqualTo true
            resideInPath("..kovariable..variable-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("kovariable/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kovariable/snippet/forkopathprovider/", fileName)
}
