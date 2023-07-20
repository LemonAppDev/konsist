package com.lemonappdev.konsist.core.container.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForNamedDeclarationTest {

    @Test
    fun `file-contains-all-type-of-named-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-named-declarations")

        // then
        sut
            .declarations()
            .filterIsInstance<KoNameProvider>()
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

    @Test
    fun `file-contains-package-and-nested-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-nested-declarations")

        // then
        sut
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "SampleClass",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-package-and-local-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-package-and-local-declarations")

        // then
        sut
            .declarations(includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "sampleFunction",
                    "sampleLocalProperty",
                ),
            )
    }

    @Test
    fun `file-contains-imports-and-nested-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-imports-and-nested-declarations")

        // then
        sut
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleType1",
                    "com.lemonappdev.konsist.testdata.SampleType2",
                    "SampleClass",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-imports-and-local-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-imports-and-local-declarations")

        // then
        sut
            .declarations(includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "com.lemonappdev.konsist.testdata.SampleType1",
                    "com.lemonappdev.konsist.testdata.SampleType2",
                    "sampleFunction",
                    "sampleLocalProperty",
                ),
            )
    }

    @Test
    fun `file-contains-annotations-and-nested-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-annotations-and-nested-declarations")

        // then
        sut
            .declarations(includeNested = true)
            .filterNot { it is KoImportDeclaration }
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleAnnotation1",
                    "SampleAnnotation2",
                    "SampleClass",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-annotations-and-local-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-annotations-and-local-declarations")

        // then
        sut
            .declarations(includeLocal = true)
            .filterNot { it is KoImportDeclaration }
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleAnnotation1",
                    "SampleAnnotation2",
                    "sampleFunction",
                    "sampleLocalProperty",
                ),
            )
    }

    @Test
    fun `file-contains-typealias-and-nested-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-typealias-and-nested-declarations")

        // then
        sut
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleClass",
                    "sampleNestedFunction",
                    "SampleTypeAlias",
                ),
            )
    }

    @Test
    fun `file-contains-typealias-and-local-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-typealias-and-local-declarations")

        // then
        sut
            .declarations(includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "sampleFunction",
                    "sampleLocalProperty",
                    "SampleTypeAlias",
                ),
            )
    }

    @Test
    fun `file-contains-one-class-containing-function`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-function")

        // then
        sut
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleClass",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-one-class-containing-function-and-property includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-function-and-property")

        // then
        sut
            .declarations(includeNested = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "SampleClass",
                    "sampleNestedProperty",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-one-class-containing-function-and-property includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-function-and-property")

        // then
        sut
            .declarations(includeNested = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("SampleClass"))
    }

    @Test
    fun `file-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = false, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.lemonappdev.konsist.testdata.SampleType1",
                    "SampleClass",
                ),
            )
    }

    @Test
    fun `file-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = true, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.lemonappdev.konsist.testdata.SampleType1",
                    "SampleClass",
                    "sampleNestedProperty1",
                    "sampleNestedFunction",
                ),
            )
    }

    @Test
    fun `file-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = false, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.lemonappdev.konsist.testdata.SampleType1",
                    "SampleClass",
                ),
            )
    }

    @Test
    fun `file-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-nested-and-local-declarations")

        // then
        sut
            .declarations(includeNested = true, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.lemonappdev.konsist.testdata.SampleType1",
                    "SampleClass",
                    "sampleNestedProperty1",
                    "sampleNestedFunction",
                    "sampleLocalFunction",
                    "SampleLocalClass",
                    "sampleNestedProperty2",
                ),
            )
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/container/koscope/snippet/fornameddeclaration/", fileName)
}
