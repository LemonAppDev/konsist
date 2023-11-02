package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoPropertyDeclarationTest {
    @Test
    fun `scope-contains-no-properties`() {
        // given
        val sut = getSnippetFile("scope-contains-no-properties")

        // then
        sut.properties(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-contains-nested-properties includeNested true`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-properties")

        // then
        val expected = listOf("sampleProperty", "sampleNestedProperty")

        sut.properties(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-nested-properties includeNested false`() {
        // given
        val sut = getSnippetFile("scope-contains-nested-properties")

        // then
        val expected = listOf("sampleProperty")

        sut.properties(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/container/snippet/forkopropertydeclaration/", fileName)
}
