package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFileForDeclarationTest {
    @Test
    fun `file-contains-all-type-of-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations")
            .files()
            .first()

        // then
        sut
            .declarations()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleAnnotation1",
                    "SampleAnnotation2",
                    "samplepackage",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation1",
                    "com.lemonappdev.konsist.testdata.SampleAnnotation2",
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            )
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `file-contains-all-type-of-declarations-with-nested-and-local-declarations`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")
            .files()
            .first()

        // then
        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/fordeclaration/", fileName)

    companion object {
        @Suppress("unused", "detekt.LongMethod")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(
                false,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            ),
            arguments(
                true,
                false,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "sampleNestedPropertyInsideClass",
                    "sampleNestedFunctionInsideClass",
                    "sampleNestedClassInsideClass",
                    "SampleInterface",
                    "sampleNestedPropertyInsideInterface",
                    "sampleNestedFunctionInsideInterface",
                    "sampleNestedClassInsideInterface",
                    "SampleObject",
                    "sampleNestedPropertyInsideObject",
                    "sampleNestedFunctionInsideObject",
                    "sampleNestedClassInsideObject",
                    "SampleTypeAlias",
                ),
            ),
            arguments(
                false,
                true,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction",
                    "sampleLocalClass2",
                    "sampleLocalProperty2",
                    "sampleLocalClass1",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            ),
            arguments(
                true,
                true,
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "sampleLocalProperty1",
                    "sampleLocalFunction",
                    "sampleLocalClass2",
                    "sampleLocalProperty2",
                    "sampleLocalClass1",
                    "sampleNestedFunction",
                    "SampleClass",
                    "sampleNestedPropertyInsideClass",
                    "sampleNestedFunctionInsideClass",
                    "sampleLocalProperty3",
                    "sampleLocalClass3",
                    "sampleNestedClassInsideClass",
                    "SampleInterface",
                    "sampleNestedPropertyInsideInterface",
                    "sampleNestedFunctionInsideInterface",
                    "sampleNestedClassInsideInterface",
                    "SampleObject",
                    "sampleNestedPropertyInsideObject",
                    "sampleNestedFunctionInsideObject",
                    "sampleNestedClassInsideObject",
                    "SampleTypeAlias",
                ),
            ),
        )
    }
}
