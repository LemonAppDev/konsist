package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForHashCodeTest {
    @Test
    fun `files-with-the-same-hashcode`() {
        // given
        val file1 = getSnippetFile("files-with-the-same-hashcode")
            .files
            .first()

        val file2 = getSnippetFile("files-with-the-same-hashcode")
            .files
            .first()

        // then
        file1.hashCode() shouldBeEqualTo file2.hashCode()
    }

    @Test
    fun `files-with-the-different-hashcode`() {
        // given
        val file1 = getSnippetFile("files-with-the-different-hashcode")
            .files
            .first()

        val file2 = getSnippetFile("files-with-the-same-hashcode")
            .files
            .first()

        // then
        file1.hashCode() shouldNotBeEqualTo file2.hashCode()
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forhashcode/", fileName)
}
