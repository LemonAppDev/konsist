package com.lemonappdev.konsist.core.scope.koscope

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForPropertyTest {

    @Test
    fun `file-contains-one-property`() {
        // given
        val sut = getSnippetFile("file-contains-one-property")

        // then
        sut
            .properties()
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleProperty"))
    }

    @Test
    fun `file-contains-no-property`() {
        // given
        val sut = getSnippetFile("file-contains-no-property")

        // then
        sut
            .properties()
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-class-containing-nested-properties includeNested true includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-class-containing-nested-properties")
            .properties(includeNested = true, includeLocal = false)

        // then
        sut
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleNestedProperty"))
    }

    @Test
    fun `file-contains-class-containing-nested-properties includeNested false includeLocal false`() {
        // given
        val sut = getSnippetFile("file-contains-class-containing-nested-properties")
            .properties(includeNested = false, includeLocal = false)
        // then
        sut
            .map { it.name }
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    @Test
    fun `file-contains-class-containing-nested-properties includeNested false includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-class-containing-nested-properties")
            .properties(includeNested = false, includeLocal = true)

        // then
        sut
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleLocalProperty2"))
    }

    @Test
    fun `file-contains-class-containing-nested-properties includeNested true includeLocal true`() {
        // given
        val sut = getSnippetFile("file-contains-class-containing-nested-properties")
            .properties(includeNested = true, includeLocal = true)

        // then
        sut
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf(
                "sampleNestedProperty",
                "sampleLocalProperty1",
                "sampleLocalProperty2"
            ))
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forproperty/", fileName)
}
