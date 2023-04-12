package com.lemon.konsist.core.declaration.koscope

import com.lemon.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForInterfaceTest {
    @Test
    fun `file-contains-one-interface`() {
        // given
        val sut = getSut("file-contains-one-interface")

        // then
        sut
            .interfaces()
            .map { it.name }
            .toList() shouldBeEqualTo listOf("SampleInterface")
    }

    @Test
    fun `file-contains-no-interface`() {
        // given
        val sut = getSut("file-contains-no-interface")

        // then
        sut
            .interfaces()
            .toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `file-contains-two-interfaces-with-nested-interface includeNested true`() {
        // given
        val sut = getSut("file-contains-two-interfaces-with-nested-interface")

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name }
            .toList() shouldBeEqualTo listOf("SampleInterface1", "SampleNestedInterface", "SampleInterface2")
    }

    @Test
    fun `file-contains-two-interfaces-with-nested-interface includeNested false`() {
        // given
        val sut = getSut("file-contains-two-interfaces-with-nested-interface")

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name }
            .toList() shouldBeEqualTo listOf("SampleInterface1", "SampleInterface2")
    }

    private fun getSut(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/declaration/koscope/snippet/forinterface/", fileName)
}
