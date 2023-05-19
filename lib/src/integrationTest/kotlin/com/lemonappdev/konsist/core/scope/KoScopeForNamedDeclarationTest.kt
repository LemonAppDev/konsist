package com.lemonappdev.konsist.core.scope

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.declaration.KoImportDeclaration
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForNamedDeclarationTest {

    @Test
    fun `file-contains-all-type-of-named-declarations`() {
        // given
        val sut = getSnippetFile("file-contains-all-type-of-named-declarations")

        // then
        sut
            .namedDeclarations()
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
            .namedDeclarations(includeNested = true)
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
            .namedDeclarations(includeLocal = true)
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
            .namedDeclarations(includeNested = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "com.sampleimport1",
                    "com.sampleimport2",
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
            .namedDeclarations(includeLocal = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "com.sampleimport1",
                    "com.sampleimport2",
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
            .namedDeclarations(includeNested = true)
            .filterNot { it is KoImportDeclaration }
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
            .namedDeclarations(includeLocal = true)
            .filterNot { it is KoImportDeclaration }
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
            .namedDeclarations(includeNested = true)
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
            .namedDeclarations(includeLocal = true)
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
            .namedDeclarations(includeNested = true)
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
            .namedDeclarations(includeNested = true)
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
            .namedDeclarations(includeNested = false)
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
            .namedDeclarations(includeNested = false, includeLocal = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.sampleimport",
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
            .namedDeclarations(includeNested = true, includeLocal = false)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.sampleimport",
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
            .namedDeclarations(includeNested = false, includeLocal = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.sampleimport",
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
            .namedDeclarations(includeNested = true, includeLocal = true)
            .map { it.name }
            .toList()
            .shouldBeEqualTo(
                listOf(
                    "samplepackage",
                    "com.sampleimport",
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
        TestSnippetProvider.getSnippetKoScope("core/scope/snippet/fornameddeclaration/", fileName)
}
