package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoClassProviderTest {
    @Test
    fun `init-block-contains-no-classes`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        sut.classes(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass", "SampleNestedClass", "SampleLocalClass")

        sut.classes(includeNested = true, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass", "SampleNestedClass")

        sut.classes(includeNested = true, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass", "SampleLocalClass")

        sut.classes(includeNested = false, includeLocal = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        val expected = listOf("SampleClass")

        sut.classes(includeNested = false, includeLocal = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-classes`() {
        // given
        val sut = getSnippetFile("contains-classes")
            .classes()
            .first()
            .initBlocks
            .first()

        // then
        assertSoftly(sut) {
            numClasses() shouldBeEqualTo 1
            numClasses(includeNested = true) shouldBeEqualTo 2
            numClasses(includeLocal = true) shouldBeEqualTo 2
            numClasses(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            containsClass("SampleClass") shouldBeEqualTo true
            containsClass("SampleNestedClass", includeNested = false) shouldBeEqualTo false
            containsClass("SampleNestedClass", includeNested = true) shouldBeEqualTo true
            containsClass("SampleLocalClass", includeLocal = false) shouldBeEqualTo false
            containsClass("SampleLocalClass", includeLocal = true) shouldBeEqualTo true
            containsClass("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkoclassprovider/", fileName)
}
