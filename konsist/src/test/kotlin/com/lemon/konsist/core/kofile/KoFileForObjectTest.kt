package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForObjectTest {
    @Test
    fun `file-with-one-object includeNested true`() {
        // given
        val sut = getSut("file-with-one-object")

        // then
        sut.objects(includeNested = true).map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object includeNested false`() {
        // given
        val sut = getSut("file-with-one-object")

        // then
        sut.objects(includeNested = false).map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested true`() {
        // given
        val sut = getSut("file-with-one-object-containing-object")

        // then
        sut.objects(includeNested = true).map { it.name } shouldBeEqualTo listOf("SampleObject", "SampleNestedObject")
    }

    @Test
    fun `file-with-one-object-containing-object includeNested false`() {
        // given
        val sut = getSut("file-with-one-object-containing-object")

        // then
        sut.objects(includeNested = false).map { it.name } shouldBeEqualTo listOf("SampleObject")
    }

    @Test
    fun `file-with-two-objects includeNested true`() {
        // given
        val sut = getSut("file-with-two-objects")

        // then
        sut.objects(includeNested = true).map { it.name } shouldBeEqualTo listOf("SampleObject1", "SampleObject2")
    }

    @Test
    fun `file-with-two-objects includeNested false`() {
        // given
        val sut = getSut("file-with-two-objects")

        // then
        sut.objects(includeNested = false).map { it.name } shouldBeEqualTo listOf("SampleObject1", "SampleObject2")
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forobject/$fileName.kt.txt")
}
