package com.lemonappdev.konsist.core.container

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoScopeForKoCompanionObjectDeclarationTest {
    @Test
    fun `scope-contains-no-companion-objects`() {
        // given
        val sut = getSnippetFile("scope-contains-no-companion-objects")

        // then
        sut.companionObjects(includeNested = true) shouldBeEqualTo emptyList()
    }

    @Test
    fun `scope-contains-companion-objects includeNested true`() {
        // given
        val sut = getSnippetFile("scope-contains-companion-objects")

        // then
        val expected = listOf("SampleNestedCompanionObject")

        sut
            .companionObjects(includeNested = true)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    @Test
    fun `scope-contains-companion-objects includeNested false`() {
        // given
        val sut = getSnippetFile("scope-contains-companion-objects")

        // then
        val expected = emptyList<String>()

        sut
            .companionObjects(includeNested = false)
            .map { it.name }
            .shouldBeEqualTo(expected)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/container/snippet/forkocompanionobjectdeclaration/", fileName)
}
