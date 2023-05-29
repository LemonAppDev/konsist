package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeForPropertyTest {

    @Test
    fun `file-contains-no-property`() {
        // given
        val sut = getSnippetFile("file-contains-no-property")

        // then
        sut
            .properties()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-one-property`() {
        // given
        val sut = getSnippetFile("file-contains-one-property")

        // then
        sut
            .properties()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleProperty"))
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `file-contains-class-containing-nested-properties`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("file-contains-class-containing-nested-properties")

        // then
        sut
            .properties(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/koscope/snippet/forproperty/".toNormalizedPath(), fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                false,
                false,
                emptyList<String>(),
            ),
            arguments(
                true,
                false,
                listOf("sampleNestedProperty"),
            ),
            arguments(
                false,
                true,
                listOf("sampleLocalProperty2"),
            ),
            arguments(
                true,
                true,
                listOf(
                    "sampleNestedProperty",
                    "sampleLocalProperty1",
                    "sampleLocalProperty2",
                ),
            ),
        )
    }
}
