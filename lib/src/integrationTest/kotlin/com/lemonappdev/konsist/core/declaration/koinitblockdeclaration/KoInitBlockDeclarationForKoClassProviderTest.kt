package com.lemonappdev.konsist.core.declaration.koinitblockdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoClassDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource

class KoInitBlockDeclarationForKoClassProviderTest {
    @Test
    fun `init-block-contains-no-classes`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-classes")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut?.classes(includeNested = true, includeLocal = true)?.toList() shouldBeEqualTo emptyList()
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("SampleClass", "SampleNestedClass", "SampleLocalClass")

        sut?.classes(includeNested = true, includeLocal = true)
            ?.toList()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("SampleClass", "SampleNestedClass")

        sut?.classes(includeNested = true, includeLocal = false)
            ?.toList()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("SampleClass", "SampleLocalClass")

        sut?.classes(includeNested = false, includeLocal = true)
            ?.toList()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-classes includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-classes")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("SampleClass")

        sut?.classes(includeNested = false, includeLocal = false)
            ?.toList()
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-classes`() {
        // given
        val sut = getSnippetFile("contains-classes")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly(sut) {
            it?.numClasses(includeNested = false) shouldBeEqualTo 1
            it?.numClasses(includeNested = true) shouldBeEqualTo 2
            it?.containsClass("SampleClass", includeNested = false) shouldBeEqualTo true
            it?.containsClass("SampleNestedClass", includeNested = false) shouldBeEqualTo false
            it?.containsClass("SampleNestedClass", includeNested = true) shouldBeEqualTo true
            it?.containsClass("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkoclassprovider/", fileName)
}
