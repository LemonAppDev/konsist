package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoTextProviderTest {
    @Test
    fun `file-text`() {
        // given
        val sut =
            getSnippetFile("file-text")
                .files
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                fun sampleFunction() {
                    "SampleText"
                }
                
                """.trimIndent(),
            )
    }

    @Test
    fun `file-has-text-with-prefix`() {
        // given
        val sut =
            getSnippetFile("file-has-text-with-prefix")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasTextStartingWith("fun sample") shouldBeEqualTo true
            hasTextStartingWith("wrong-prefix") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-text-with-suffix`() {
        // given
        val sut =
            getSnippetFile("file-has-text-with-suffix")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasTextEndingWith("\n") shouldBeEqualTo true
            hasTextEndingWith("wrong-suffix") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-text-containing-text`() {
        // given
        val sut =
            getSnippetFile("file-has-text-containing-text")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasTextContaining("SampleText") shouldBeEqualTo true
            hasTextContaining("not-containing") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-text-matching-regex`() {
        // given
        val sut =
            getSnippetFile("file-has-text-matching-regex")
                .files
                .first()

        // then
        assertSoftly(sut) {
            hasTextMatching(Regex("^[a-zA-Z[^0-9]]+")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkotextprovider/",
            fileName,
        )
}
