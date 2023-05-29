package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForNameAndExtensionTest {
    @Test
    fun `file-name`() {
        // given
        val sut = getSnippetFile("file-name")
            .files()
            .first()

        // then
        sut.name shouldBeEqualTo "file-name"
    }

    @Test
    fun `file-extension`() {
        // given
        val sut = getSnippetFile("file-extension")
            .files()
            .first()

        // then
        // The snippet file has '.kttxt' extension, but in name we have '.kt' because KotlinFileParser replace the first one into second one
        sut.extension shouldBeEqualTo "kt"
    }

    @Test
    fun `file-name-with-extension`() {
        // given
        val sut = getSnippetFile("file-name-with-extension")
            .files()
            .first()

        // then
        // The snippet file has '.kttxt' extension, but in name we have '.kt' because KotlinFileParser replace the first one into second one
        sut.nameWithExtension shouldBeEqualTo "file-name-with-extension.kt"
    }

    @Test
    fun `file-has-name-with-prefix`() {
        // given
        val sut = getSnippetFile("file-has-name-with-prefix")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasNameStartingWith("file-has-name") shouldBeEqualTo true
            hasNameStartingWith("wrong-prefix") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-name-with-suffix`() {
        // given
        val sut = getSnippetFile("file-has-name-with-suffix")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasNameEndingWith("with-suffix") shouldBeEqualTo true
            hasNameEndingWith("wrong-suffix") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-name-containing-text`() {
        // given
        val sut = getSnippetFile("file-has-name-containing-text")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasNameContaining("name-containing") shouldBeEqualTo true
            hasNameContaining("not-containing") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-name-matching-regex`() {
        // given
        val sut = getSnippetFile("file-has-name-matching-regex")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasNameMatching(Regex("^[a-zA-Z[^0-9]]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `file-has-extension`() {
        // given
        val sut = getSnippetFile("file-has-extension")
            .files()
            .first()

        // then
        assertSoftly(sut) {
            hasExtension("kt") shouldBeEqualTo true
            hasExtension("java") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/fornameandextension/", fileName)
}
