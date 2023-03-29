package com.lemon.konsist.core.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForInterfaceTest {
    @Test
    fun `file-with-one-interface`() {
        // given
        val sut = getSut("file-with-one-interface")

        // then
        sut
            .interfaces()
            .map { it.name } shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-without-interface`() {
        // given
        val sut = getSut("file-without-interface")

        // then
        sut
            .interfaces()
            .isEmpty()
    }

    @Test
    fun `file-with-two-interfaces-with-nested-class`() {
        // given
        val sut = getSut("file-with-two-interfaces-with-nested-interface")

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name } shouldBeEqualTo listOf("SampleInterface1", "SampleInterface2", "NestedSampleInterface")
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/koscope/snippet/forinterface/$fileName.kt.txt")
}
