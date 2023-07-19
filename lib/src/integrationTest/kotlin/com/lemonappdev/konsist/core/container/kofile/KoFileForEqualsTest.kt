package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForEqualsTest {
    @Test
    fun `files-are-equal`() {
        // given
        val file1 = getSnippetFile("files-are-equal")
            .files
            .first()

        val file2 = getSnippetFile("files-are-equal")
            .files
            .first()

        // then
        file1 shouldBeEqualTo file2
    }

    @Test
    fun `files-are-not-equal`() {
        // given
        val file1 = getSnippetFile("files-are-not-equal")
            .files
            .first()

        val file2 = getSnippetFile("files-are-equal")
            .files
            .first()

        // then
        file1 shouldNotBeEqualTo file2
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/forequals/", fileName)
}
