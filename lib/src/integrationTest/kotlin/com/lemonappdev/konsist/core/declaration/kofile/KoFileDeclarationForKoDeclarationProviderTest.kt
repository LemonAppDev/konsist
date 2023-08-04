package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoFileDeclarationForKoDeclarationProviderTest {
    @Test
    fun `file-contains-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-declarations")
            .files
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations() shouldBeEqualTo 2
            numDeclarations(includeNested = true) shouldBeEqualTo 3
            numDeclarations(includeLocal = true) shouldBeEqualTo 3
            numDeclarations(includeNested = true, includeLocal = true) shouldBeEqualTo 4
            containsDeclaration("SampleNestedClass") shouldBeEqualTo true
            containsDeclaration("sampleNestedProperty", includeNested = false) shouldBeEqualTo false
            containsDeclaration("sampleNestedProperty", includeNested = true) shouldBeEqualTo true
            containsDeclaration("sampleLocalProperty", includeLocal = false) shouldBeEqualTo false
            containsDeclaration("sampleLocalProperty", includeLocal = true) shouldBeEqualTo true
            containsDeclaration("NonExisting") shouldBeEqualTo false
        }
    }

    @Test
    fun `file-contains-all-type-of-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations")
            .files
            .first()

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
    fun `file-contains-all-type-of-declarations-with-nested-and-local-declarations`(
        includeNested: Boolean,
        includeLocal: Boolean,
        expected: List<String>,
    ) {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-declarations-with-nested-and-local-declarations")
            .files
            .first()

        // then
        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope(
        "core/declaration/kofile/snippet/forkodeclarationprovider/",
        fileName,
    )

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
