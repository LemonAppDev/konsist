package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoDeclarationProviderTest {
    @Test
    fun `object-contains-no-declarations`() {
        // given
        val sut = getSnippetFile("object-contains-no-declarations")
            .objects()
            .first()

        // then
        sut.declarations(includeNested = true, includeLocal = true).toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
            "SampleObjectNestedInsideClass",
            "SampleInterfaceNestedInsideClass",
            "SampleObject",
            "SampleClassNestedInsideObject",
            "SampleObjectNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleInterface",
            "SampleClassNestedInsideInterface",
            "SampleObjectNestedInsideInterface",
            "SampleInterfaceNestedInsideInterface",
        )

        sut.declarations(includeNested = true, includeLocal = true)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
            "SampleObjectNestedInsideClass",
            "SampleInterfaceNestedInsideClass",
            "SampleObject",
            "SampleClassNestedInsideObject",
            "SampleObjectNestedInsideObject",
            "SampleInterfaceNestedInsideObject",
            "SampleInterface",
            "SampleClassNestedInsideInterface",
            "SampleObjectNestedInsideInterface",
            "SampleInterfaceNestedInsideInterface",
        )

        sut.declarations(includeNested = true, includeLocal = false)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut.declarations(includeNested = false, includeLocal = true)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `object-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("object-contains-nested-and-local-declarations")
            .objects()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut.declarations(includeNested = false, includeLocal = false)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-declarations`() {
        // given
        val sut = getSnippetFile("contains-declarations")
            .objects()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations(includeNested = false) shouldBeEqualTo 1
            numDeclarations(includeNested = true) shouldBeEqualTo 2
            containsDeclarations("SampleNestedClass", includeNested = false) shouldBeEqualTo true
            containsDeclarations("sampleProperty", includeNested = false) shouldBeEqualTo false
            containsDeclarations("sampleProperty", includeNested = true) shouldBeEqualTo true
            containsDeclarations("NonExisting") shouldBeEqualTo false
        }
    }
    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkodeclarationprovider/", fileName)
}
