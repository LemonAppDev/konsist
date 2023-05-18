package com.lemonappdev.konsist.core.scope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeForFunctionTest {

    @Test
    fun `file-contains-no-function`() {
        // given
        val sut = getSnippetFile("file-contains-no-function")

        // then
        sut
            .functions()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-one-function`() {
        // given
        val sut = getSnippetFile("file-contains-one-function")

        // then
        sut
            .functions()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleFunction"))
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `file-contains-function-with-local-and-nested-functions`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("file-contains-function-with-local-and-nested-functions")

        // then
        sut
            .functions(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/snippet/forfunction/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                false,
                false,
                listOf("sampleFunction"),
            ),
            arguments(
                true,
                false,
                listOf("sampleFunction"),
            ),
            arguments(
                false,
                true,
                listOf(
                    "sampleFunction",
                    "sampleLocalFunction",
                ),
            ),
            arguments(
                true,
                true,
                listOf(
                    "sampleFunction",
                    "sampleLocalFunction",
                    "sampleNestedFunction",
                ),
            ),
        )
    }
}
