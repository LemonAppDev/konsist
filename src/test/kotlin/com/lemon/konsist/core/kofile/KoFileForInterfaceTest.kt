package com.lemon.konsist.core.kofile

import com.lemon.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForInterfaceTest {
    @Test
    fun `file-with-one-interface includeNested true`() {
        // given
        val sut = getSut("file-with-one-interface")

        // then
        sut.interfaces(includeNested = true).map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-with-one-interface includeNested false`() {
        // given
        val sut = getSut("file-with-one-interface")

        // then
        sut.interfaces(includeNested = false).map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-with-one-interface-containing-interface includeNested true`() {
        // given
        val sut = getSut("file-with-one-interface-containing-interface")

        // then
        sut.interfaces(includeNested = true).map { it.name } shouldBeEqualTo listOf("SampleInterface", "SampleNestedInterface")
    }

    @Test
    fun `file-with-one-interface-containing-interface includeNested false`() {
        // given
        val sut = getSut("file-with-one-interface-containing-interface")

        // then
        sut.interfaces(includeNested = false).map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forinterface/$fileName.kt.txt")
}
