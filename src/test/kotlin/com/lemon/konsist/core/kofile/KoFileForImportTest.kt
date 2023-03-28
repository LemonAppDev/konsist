package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForImportTest {
    @Test
    fun `file-with-one-import`() {
        // given
        val sut = getSut("file-with-one-import")

        // then
        sut.imports().map { it.name } shouldBeEqualTo listOf("com.sampleimport")
    }

    @Test
    fun `file-without-imports`() {
        // given
        val sut = getSut("file-without-imports")

        // then
        sut.imports().isEmpty()
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forimport/$fileName.kt.txt")
}
