package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoPathProviderTest {
    @Test
    fun `typealias-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-file-path")
                .typeAliases
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("kotypealias/snippet/forkopathprovider/typealias-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `typealias-project-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-project-file-path")
                .typeAliases
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/kotypealias/snippet/" +
                    "forkopathprovider/typealias-project-file-path.kt",
            )
    }

    @Test
    fun `typealias-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-reside-in-file-path")
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..kotypealias/snippet..", true) shouldBeEqualTo true
            resideInPath("..kotypealias..typealias-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("kotypealias/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `typealias-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("typealias-reside-in-project-file-path")
                .typeAliases
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..kotypealias/snippet..", false) shouldBeEqualTo true
            resideInPath("..kotypealias..typealias-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("kotypealias/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kotypealias/snippet/forkopathprovider/", fileName)
}
