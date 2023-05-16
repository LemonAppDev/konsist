package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeForClassTest {
    @Test
    fun `file-contains-no-class`() {
        // given
        val sut = getSnippetFile("file-contains-no-class")

        // then
        sut
            .classes()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-one-class`() {
        // given
        val sut = getSnippetFile("file-contains-one-class")

        // then
        sut
            .classes()
            .toList()
            .map { it.name }
            .shouldBeEqualTo(listOf("SampleClass"))
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `file-contains-class-with-local-and-nested-classes`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("file-contains-class-with-local-and-nested-classes")

        // then
        sut
            .classes(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forclass/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                false, false, listOf("SampleClass")
            ),
            arguments(
                true, false, listOf(
                    "SampleClass",
                    "SampleNestedClass1",
                    "SampleNestedClass2",
                )
            ),
            arguments(
                false, true, listOf("SampleClass")
            ),
            arguments(
                true, true, listOf(
                    "SampleClass",
                    "SampleLocalClass",
                    "SampleNestedClass1",
                    "SampleNestedClass2",
                )
            ),
        )
    }
}
