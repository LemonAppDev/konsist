package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoPathProviderTest {
    @Test
    fun `property-file-path`() {
        // given
        val sut =
            getSnippetFile("property-file-path")
                .properties()
                .first()

        // then
        assertSoftly(sut.path) {
            startsWith("//") shouldBeEqualTo false
            endsWith("koproperty/snippet/forkopathprovider/property-file-path.kt") shouldBeEqualTo true
        }
    }

    @Test
    fun `property-project-file-path`() {
        // given
        val sut =
            getSnippetFile("property-project-file-path")
                .properties()
                .first()

        // then
        sut
            .projectPath
            .shouldBeEqualTo(
                "/lib/src/integrationTest/kotlin/com/lemonappdev/konsist/core/declaration/koproperty/snippet/" +
                    "forkopathprovider/property-project-file-path.kt",
            )
    }

    @Test
    fun `property-reside-in-file-path`() {
        // given
        val sut =
            getSnippetFile("property-reside-in-file-path")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", true) shouldBeEqualTo true
            resideInPath("..koproperty/snippet..", true) shouldBeEqualTo true
            resideInPath("..koproperty..property-reside-in-file-path.kt", true) shouldBeEqualTo true
            resideInPath("koproperty/snippet/", true) shouldBeEqualTo false
        }
    }

    @Test
    fun `property-reside-in-project-file-path`() {
        // given
        val sut =
            getSnippetFile("property-reside-in-project-file-path")
                .properties()
                .first()

        // then
        assertSoftly(sut) {
            resideInPath("..snippet..", false) shouldBeEqualTo true
            resideInPath("..koproperty/snippet..", false) shouldBeEqualTo true
            resideInPath("..koproperty..property-reside-in-project-file-path.kt", false) shouldBeEqualTo true
            resideInPath("koproperty/snippet/", false) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkopathprovider/", fileName)
}
