package com.lemonappdev.konsist.core.declaration.koscopedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeDeclarationForInterfaceTest {
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
    fun `file-contains-two-interfaces-with-nested-interface includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-two-interfaces-with-nested-interface")

        // then
        sut
            .interfaces(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleInterface1",
                    "SampleNestedInterface",
                    "SampleInterface2",
                ),
            )
    }

    @Test
    fun `file-contains-two-interfaces-with-nested-interface includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-two-interfaces-with-nested-interface")

        // then
        sut
            .interfaces(includeNested = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleInterface1",
                    "SampleInterface2",
                ),
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koscope/snippet/forinterface/", fileName)
}
