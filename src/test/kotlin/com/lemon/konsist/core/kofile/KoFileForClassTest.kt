package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForClassTest {
    @Test
    fun `file-with-one-class includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-class")
                .classes(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `file-with-one-class includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-class")
                .classes(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `file-with-one-class-containing-class includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-class-containing-class")
                .classes(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass", "SampleNestedClass")
    }

    @Test
    fun `file-with-one-class-containing-class includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-class-containing-class")
                .classes(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forclass/$fileName.kt.txt")
}
