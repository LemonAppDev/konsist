package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoUnknownParentProviderTest {
    @Test
    fun `object-has-no-unknown-parent`() {
        // given
        val sut = getSnippetFile("object-has-no-unknown-parent")
            .objects()
            .first()

        // then
        sut.unknownParents shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-has-only-unknown-parents`() {
        // given
        val sut = getSnippetFile("object-has-only-unknown-parents")
            .objects()
            .first()

        // then
        sut.unknownParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    @Test
    fun `object-has-known-and-unknown-parents`() {
        // given
        val sut = getSnippetFile("object-has-known-and-unknown-parents")
            .objects()
            .first()

        // then
        sut.unknownParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkounknownparentprovider/", fileName)
}
