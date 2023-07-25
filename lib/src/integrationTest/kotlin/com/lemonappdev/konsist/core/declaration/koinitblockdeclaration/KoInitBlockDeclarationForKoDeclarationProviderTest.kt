package com.lemonappdev.konsist.core.declaration.koinitblockdeclaration

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
            ?.first()

        // then
        sut?.declarations(includeNested = true, includeLocal = true)?.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
        )

        sut?.declarations(includeNested = true, includeLocal = true)
            ?.toList()
            ?.filterIsInstance<KoNameProvider>()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
            "SampleClassNestedInsideClass",
        )

        sut?.declarations(includeNested = true, includeLocal = false)
            ?.toList()
            ?.filterIsInstance<KoNameProvider>()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf(
            "sampleFunction",
            "sampleLocalProperty",
            "SampleLocalClass",
            "sampleLocalFunction",
            "SampleClass",
        )

        sut?.declarations(includeNested = false, includeLocal = true)
            ?.toList()
            ?.filterIsInstance<KoNameProvider>()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-declarations includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf(
            "sampleFunction",
            "SampleClass",
        )

        sut?.declarations(includeNested = false, includeLocal = false)
            ?.toList()
            ?.filterIsInstance<KoNameProvider>()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-declarations`() {
        // given
        val sut = getSnippetFile("contains-declarations")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly(sut) {
            it?.numDeclarations(includeNested = false) shouldBeEqualTo 1
            it?.numDeclarations(includeNested = true) shouldBeEqualTo 2
            it?.containsDeclarations("SampleNestedClass", includeNested = false) shouldBeEqualTo true
            it?.containsDeclarations("sampleProperty", includeNested = false) shouldBeEqualTo false
            it?.containsDeclarations("sampleProperty", includeNested = true) shouldBeEqualTo true
            it?.containsDeclarations("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkodeclarationprovider/", fileName)
}
