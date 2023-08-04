package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.provider.KoNameProvider
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoDeclarationProviderTest {
    @Test
    fun `init-block-contains-no-declarations`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-declarations")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        sut.declarations(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
        )

        sut.declarations(includeNested = true, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
        )

        sut.declarations(includeNested = true, includeLocal = false)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
        )

        sut.declarations(includeNested = false, includeLocal = true)
            .filterIsInstance<KoNameProvider>()
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
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
            .initBlocks
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

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koinitblock/snippet/forkodeclarationprovider/", fileName)
}
