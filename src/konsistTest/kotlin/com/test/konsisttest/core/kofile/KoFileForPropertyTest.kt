package com.test.konsisttest.core.kofile

import com.test.konsisttest.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForPropertyTest {
    @Test
    fun `file-with-one-property`() {
        // given
        val sut = getSut("file-with-one-property")

        // then
        sut.properties().map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    @Test
    fun `file-with-two-properties includeNested false`() {
        // given
        val sut = getSut("file-with-two-properties")

        // then
        sut.properties().map { it.name } shouldBeEqualTo listOf("sampleProperty1", "sampleProperty2")
    }

    @Test
    fun `file-with-one-class-containing-property`() {
        // given
        val sut = getSut("file-with-one-property")

        // then
        sut.properties().map { it.name } shouldBeEqualTo listOf("sampleProperty")
    }

    private fun getSut(fileName: String) = getSnippetKoScope("core/kofile/snippet/forproperty/$fileName.kt.txt")
}
