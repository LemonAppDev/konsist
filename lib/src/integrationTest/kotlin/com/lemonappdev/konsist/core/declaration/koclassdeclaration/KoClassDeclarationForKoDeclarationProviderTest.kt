package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoClassDeclarationForKoDeclarationProviderTest {
    @Test
    fun `class-contains-no-declarations`() {
        // given
        val sut = getSnippetFile("class-contains-no-declarations")
            .classes()
            .first()

        // then
        sut.declarations(includeNested = true, includeLocal = true).toList() shouldBeEqualTo emptyList()
    }

    @ParameterizedTest
    @MethodSource("provideValues")
    fun `class-contains-declarations includeNested true includeLocal true`(
        includeNested: Boolean,
        includeLocal: Boolean,
    ) {
        // given
        val sut = getSnippetFile("class-contains-declarations")
            .classes()
            .first()

        // then
        val expected = listOf(
            "sampleProperty",
            "sampleFunction",
            "SampleClass",
            "SampleObject",
            "SampleInterface",
        )

        sut
            .declarations(includeNested = includeNested, includeLocal = includeLocal)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-declarations")
            .classes()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
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
    fun `class-contains-nested-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-declarations")
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
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-nested-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-nested-declarations")
            .classes()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
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
    fun `class-contains-nested-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-nested-declarations")
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
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-local-declarations")
            .classes()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty1",
            "sampleLocalFunction1",
            "sampleLocalProperty2",
            "sampleLocalFunction2",
            "SampleLocalClass1",
            "sampleLocalFunction3",
        )

        sut.declarations(includeNested = true, includeLocal = true)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-local-declarations")
            .classes()
            .first()

        // then
        val expected = listOf("sampleFunction")

        sut.declarations(includeNested = true, includeLocal = false)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("class-contains-local-declarations")
            .classes()
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty1",
            "sampleLocalFunction1",
            "sampleLocalProperty2",
            "sampleLocalFunction2",
            "SampleLocalClass1",
        )

        sut.declarations(includeNested = false, includeLocal = true)
            .toList()
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `class-contains-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("class-contains-local-declarations")
            .classes()
            .first()

        // then
        val expected = listOf("sampleFunction")

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
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkodeclarationprovider/", fileName)

    companion object {
        @Suppress("unused")
        @JvmStatic
        fun provideValues() = listOf(
            arguments(true, true),
            arguments(true, false),
            arguments(false, true),
            arguments(false, false),
        )
    }
}
