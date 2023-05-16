package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForInterfaceTest {
    @Test
    fun `file-contains-one-interface`() {
        // given
        val sut = getSnippetFile("file-contains-one-interface")

        // then
        sut
            .interfaces()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleInterface"))
    }

    @Test
    fun `file-contains-no-interface`() {
        // given
        val sut = getSnippetFile("file-contains-no-interface")

        // then
        sut
            .interfaces()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-interface-with-nested-interfaces includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-interface-with-nested-interfaces")

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleInterface1",
                    "SampleNestedInterface1",
                    "SampleNestedInterface2",
                ),
            )
    }

    @Test
    fun `file-contains-interface-with-nested-interfaces includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-interface-with-nested-interfaces")

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleInterface1"))
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forinterface/", fileName)
}
