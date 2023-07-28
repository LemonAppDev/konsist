package com.lemonappdev.konsist.core.declaration.koinitblockdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.declaration.KoPropertyDeclaration
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoPropertyProviderTest {
    @Test
    fun `init-block-contains-no-properties`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-properties")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut?.properties(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `init-block-contains-nested-and-local-properties includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-properties")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("sampleLocalProperty", "sampleNestedProperty")

        sut?.properties(includeNested = true, includeLocal = true)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-properties includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-properties")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("sampleNestedProperty")

        sut?.properties(includeNested = true, includeLocal = false)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-properties includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-properties")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("sampleLocalProperty")

        sut?.properties(includeNested = false, includeLocal = true)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-properties includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-properties")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = emptyList<KoPropertyDeclaration>()

        sut?.properties(includeNested = false, includeLocal = false)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-properties`() {
        // given
        val sut = getSnippetFile("contains-properties")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly(sut) {
            it?.numProperties(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            it?.numProperties(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            it?.numProperties(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            it?.numProperties(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            it?.containsProperty("sampleProperty", includeNested = false, includeLocal = false) shouldBeEqualTo true
            it?.containsProperty("sampleLocalProperty", includeNested = false, includeLocal = true) shouldBeEqualTo true
            it?.containsProperty("sampleLocalProperty", includeNested = false, includeLocal = false) shouldBeEqualTo false
            it?.containsProperty("sampleNestedProperty", includeNested = true, includeLocal = false) shouldBeEqualTo true
            it?.containsProperty("sampleNestedProperty", includeNested = false, includeLocal = false) shouldBeEqualTo false
            it?.containsProperty("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkopropertyprovider/", fileName)
}
