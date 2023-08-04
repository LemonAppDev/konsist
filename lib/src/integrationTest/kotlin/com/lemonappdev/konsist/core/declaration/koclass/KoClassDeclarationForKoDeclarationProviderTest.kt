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
    fun `contains-declarations-when-all-declaration-have-name`() {
        // given
        val sut = getSnippetFile("contains-declarations-when-all-declaration-have-name")
            .classes()
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
    fun `contains-declarations-when-some-declaration-have-no-name`() {
        // given
        val sut = getSnippetFile("contains-declarations-when-some-declaration-have-no-name")
            .classes()
            .first()

        // then
        assertSoftly(sut) {
            numDeclarations(includeNested = false) shouldBeEqualTo 1
            numDeclarations(includeNested = true) shouldBeEqualTo 2
            containsDeclarations("sampleProperty", includeNested = false) shouldBeEqualTo false
            containsDeclarations("sampleProperty", includeNested = true) shouldBeEqualTo true
            containsDeclarations("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkodeclarationprovider/", fileName)
}
