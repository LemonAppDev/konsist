package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoFunctionProviderTest {
    @Test
    fun `init-block-contains-no-functions`() {
        // given
        val sut = getSnippetFile("init-block-contains-no-functions")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut?.functions(includeNested = true, includeLocal = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `init-block-contains-nested-and-local-functions includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-functions")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction", "sampleNestedFunction")

        sut?.functions(includeNested = true, includeLocal = true)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-functions includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-functions")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("sampleFunction", "sampleNestedFunction")

        sut?.functions(includeNested = true, includeLocal = false)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-functions includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-functions")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("sampleFunction", "sampleLocalFunction")

        sut?.functions(includeNested = false, includeLocal = true)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `init-block-contains-nested-and-local-functions includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("init-block-contains-nested-and-local-functions")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        val expected = listOf("sampleFunction")

        sut?.functions(includeNested = false, includeLocal = false)
            ?.map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `contains-functions`() {
        // given
        val sut = getSnippetFile("contains-functions")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        assertSoftly(sut) {
            it?.numFunctions(includeNested = true, includeLocal = true) shouldBeEqualTo 3
            it?.numFunctions(includeNested = true, includeLocal = false) shouldBeEqualTo 2
            it?.numFunctions(includeNested = false, includeLocal = true) shouldBeEqualTo 2
            it?.numFunctions(includeNested = false, includeLocal = false) shouldBeEqualTo 1
            it?.containsFunction("sampleFunction", includeNested = false, includeLocal = false) shouldBeEqualTo true
            it?.containsFunction("sampleLocalFunction", includeNested = false, includeLocal = true) shouldBeEqualTo true
            it?.containsFunction("sampleLocalFunction", includeNested = false, includeLocal = false) shouldBeEqualTo false
            it?.containsFunction("sampleNestedFunction", includeNested = true, includeLocal = false) shouldBeEqualTo true
            it?.containsFunction("sampleNestedFunction", includeNested = false, includeLocal = false) shouldBeEqualTo false
            it?.containsFunction("NonExisting") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkofunctionprovider/", fileName)
}
