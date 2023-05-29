package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.core.ext.toNormalizedPath
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeForObjectTest {
    @Test
    fun `file-contains-no-object`() {
        // given
        val sut = getSnippetFile("file-contains-no-object")

        // then
        sut
            .objects()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-one-object`() {
        // given
        val sut = getSnippetFile("file-contains-one-object")

        // then
        sut
            .objects()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleObject"))
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `file-contains-object-with-nested-objects`(
        includeNested: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("file-contains-object-with-nested-objects")

        // then
        sut
            .objects(includeNested = includeNested)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/koscope/snippet/forobject/".toNormalizedPath(), fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                true,
                listOf(
                    "SampleObject",
                    "SampleNestedObject1",
                    "SampleNestedObject2",
                ),
            ),
            arguments(
                false,
                listOf("SampleObject"),
            ),
        )
    }
}
