package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationTest {
    @Test
    fun `file-to-string`() {
        // given
        val sut =
            getSnippetFile("file-to-string")
                .files
                .first()

        // then
        sut.toString() shouldBeEqualTo sut.path
    }

    @Test
    fun `files-are-equal`() {
        // given
        val file1 =
            getSnippetFile("files-are-equal")
                .files
                .first()

        val file2 =
            getSnippetFile("files-are-equal")
                .files
                .first()

        // then
        file1 shouldBeEqualTo file2
    }

    @Test
    fun `files-are-not-equal`() {
        // given
        val file1 =
            getSnippetFile("files-are-not-equal")
                .files
                .first()

        val file2 =
            getSnippetFile("files-are-equal")
                .files
                .first()

        // then
        file1 shouldNotBeEqualTo file2
    }

    @Test
    fun `files-with-the-same-hashcode`() {
        // given
        val file1 =
            getSnippetFile("files-with-the-same-hashcode")
                .files
                .first()

        val file2 =
            getSnippetFile("files-with-the-same-hashcode")
                .files
                .first()

        // then
        file1.hashCode() shouldBeEqualTo file2.hashCode()
    }

    @Test
    fun `files-with-the-different-hashcode`() {
        // given
        val file1 =
            getSnippetFile("files-with-the-different-hashcode")
                .files
                .first()

        val file2 =
            getSnippetFile("files-with-the-same-hashcode")
                .files
                .first()

        // then
        file1.hashCode() shouldNotBeEqualTo file2.hashCode()
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofile/snippet/forgeneral/", fileName)
}
