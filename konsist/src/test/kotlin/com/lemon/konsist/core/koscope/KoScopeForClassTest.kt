package com.lemon.konsist.core.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForClassTest {

    @Test
    fun `file-with-one-class`() {
        // given
        val sut = getSut("file-with-one-class")

        // then
        sut.classes().map { it.name } shouldBeEqualTo listOf("SampleClass")
    }

    @Test
    fun `file-without-class`() {
        // given
        val sut = getSut("file-without-class")

        // then
        sut.classes().isEmpty()
    }

    @Test
    fun `file-with-two-classes-with-nested-class`() {
        // given
        val sut = getSut("file-with-two-classes-with-nested-class")

        // then
        sut.classes(includeNested = true).map { it.name } shouldBeEqualTo listOf(
            "SampleClass1",
            "SampleClass2",
            "NestedSampleClass",
        )
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koscope/snippet/forclass/$fileName.kt.txt")
}
