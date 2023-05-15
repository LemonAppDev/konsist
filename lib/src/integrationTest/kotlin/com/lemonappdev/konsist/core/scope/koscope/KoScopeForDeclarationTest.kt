package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForDeclarationTest {
    @Test
    fun `file-contains-all-type-of-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations")

        // then
        sut
            .declarations()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            )
    }

    @Test
    fun `file-contains-all-type-of-declarations-with-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = false, includeLocal = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleProperty",
                    "sampleFunction",
                    "SampleClass",
                    "SampleInterface",
                    "SampleObject",
                    "SampleTypeAlias",
                ),
            )
    }

    @Test
    fun `file-contains-all-type-of-declarations-with-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = true, includeLocal = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
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
            )
    }

    @Test
    fun `file-contains-all-type-of-declarations-with-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = false, includeLocal = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
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
            )
    }

    @Test
    fun `file-contains-all-type-of-declarations-with-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
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
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/fordeclaration/", fileName)
}
