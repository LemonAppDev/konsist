package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoObjectDeclarationTest {
    @Test
    fun `scope-contains-no-objects`() {
        // given
        val sut = getSnippetFile("scope-contains-no-objects")

        // then
        sut.objects(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-contains-objects includeNested true`() {
        // given
        val sut = getSnippetFile("scope-contains-objects")

        // then
        val expected = listOf("FixtureObject", "FixtureNestedObject")

        sut
            .objects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-objects includeNested false`() {
        // given
        val sut = getSnippetFile("scope-contains-objects")

        // then
        val expected = listOf("FixtureObject")

        sut
            .objects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/snippet/forkoobjectdeclaration/", fileName)
}
