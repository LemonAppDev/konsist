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
    fun `file-contains-one-class-containing-property includeNested true`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-property")
            .properties(includeNested = true)

        // then
        sut
            .map { it.name }
            .toList()
            .shouldBeEqualTo(listOf("sampleNestedProperty"))
    }

    @Test
    fun `file-contains-one-class-containing-property includeNested false`() {
        // given
        val sut = getSnippetFile("file-contains-one-class-containing-property")
            .properties(includeNested = false)
        // then
        sut
            .map { it.name }
            .toList()
            .shouldBeEqualTo(emptyList())
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/scope/koscope/snippet/forproperty/", fileName)
}
