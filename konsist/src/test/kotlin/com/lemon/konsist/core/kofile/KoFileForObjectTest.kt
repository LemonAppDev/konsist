package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForObjectTest {
    @Test
    fun `file-with-one-object includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-object")
                .objects(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-object")
                .objects(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested true`() {
        // given
        val sut =
            getSut("file-with-one-object-containing-object")
                .objects(includeNested = true)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject", "SampleNestedObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested false`() {
        // given
        val sut =
            getSut("file-with-one-object-containing-object")
                .objects(includeNested = false)

        // then
        sut.map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forobject/$fileName.kt.txt")
}
