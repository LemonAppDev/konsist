package com.test.konsisttest.core.kofile

import com.test.konsisttest.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForFunctionTest {
    @Test
    fun `file-with-one-function includeNested true`() {
        // given
        val sut = getSut("file-with-one-function")

        // then
        sut.functions(includeNested = true).map { it.name } shouldBeEqualTo listOf("SampleFunction")
    }

    @Test
    fun `file-with-one-function includeNested false`() {
        // given
        val sut = getSut("file-with-one-function")

        // then
        sut.functions(includeNested = false).map { it.name } shouldBeEqualTo listOf("SampleFunction")
    }

    @Test
    fun `file-with-two-functions includeNested false`() {
        // given
        val sut = getSut("file-with-two-functions")

        // then
        sut.functions(includeNested = false).map { it.name } shouldBeEqualTo listOf("SampleFunction1", "SampleFunction2")
    }

    @Test
    fun `file-with-one-class-containing-function includeNested true`() {
        // given
        val sut = getSut("file-with-one-class-containing-function")

        // then
        sut.functions(includeNested = true).map { it.name } shouldBeEqualTo listOf("SampleFunction")
    }

    @Test
    fun `file-with-one-class-containing-function includeNested false`() {
        // given
        val sut = getSut("file-with-one-class-containing-function")

        // then
        sut.functions(includeNested = false).map { it.name } shouldBeEqualTo emptyList()
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forfunction/$fileName.kt.txt")
}
