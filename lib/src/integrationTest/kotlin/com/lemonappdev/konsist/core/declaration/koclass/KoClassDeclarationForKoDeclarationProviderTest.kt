package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoDeclarationProviderTest {
    @Test
    fun `class-contains-no-declarations`() {
        // given
        val sut = getSnippetFile("class-contains-no-declarations")
            .classes()
            .first()

        // then
        sut.declarations(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `class-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-declarations")
            .classes()
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
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-declarations")
            .classes()
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
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-declarations")
            .classes()
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
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-and-local-declarations")
            .classes()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut.declarations(includeNested = false, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-declarations`() {
        // given
        val sut = getSnippetFile("contains-declarations")
            .classes()
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
        getSnippetKoScope("core/declaration/koclass/snippet/forkodeclarationprovider/", fileName)
}
