package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoScopeForKoDeclarationProviderTest {
    @Test
    fun `scope-contains-declarations`() {
        // given
        val sut = getSnippetFile("scope-contains-declarations")

        // then
        assertSoftly(sut) {
            numDeclarations(includeNested = false) shouldBeEqualTo 1
            numDeclarations(includeNested = true) shouldBeEqualTo 2
            containsDeclarations("SampleInterface") shouldBeEqualTo true
            containsDeclarations("sampleNestedClass", includeNested = true) shouldBeEqualTo true
            containsDeclarations("sampleNestedClass", includeNested = false) shouldBeEqualTo false
        }
    }

    @Test
    fun `scope-contains-all-type-of-declarations`() {
        // given
        val sut = getSnippetFile("scope-contains-all-type-of-declarations")

        // then
        sut
            .declarations()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
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
    fun `scope-contains-all-type-of-declarations-with-nested-and-local-declarations`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("scope-contains-all-type-of-declarations-with-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/koscope/snippet/forkodeclarationprovider/", fileName)

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
