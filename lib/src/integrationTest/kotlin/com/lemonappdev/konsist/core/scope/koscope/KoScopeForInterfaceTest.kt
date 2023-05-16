package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeForInterfaceTest {
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

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `file-contains-interface-with-nested-interfaces`(
        includeNested: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("file-contains-interface-with-nested-interfaces")

        // then
        sut
            .interfaces(includeNested = includeNested)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forinterface/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                true,
                listOf(
                    "SampleInterface1",
                    "SampleNestedInterface1",
                    "SampleNestedInterface2",
                ),
            ),
            arguments(
                false,
                listOf("SampleInterface1"),
            ),
        )
    }
}
