package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoExternalParentProviderTest {
    @Test
    fun `object-has-no-external-parent`() {
        // given
        val sut = getSnippetFile("object-has-no-external-parent")
            .objects()
            .first()

        // then
        sut.externalParents shouldBeEqualTo emptyList()
    }

    @Test
    fun `object-has-only-external-parents`() {
        // given
        val sut = getSnippetFile("object-has-only-external-parents")
            .objects()
            .first()

        // then
        sut.externalParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    @Test
    fun `object-has-internal-and-external-parents`() {
        // given
        val sut = getSnippetFile("object-has-internal-and-external-parents")
            .objects()
            .first()

        // then
        sut.externalParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobject/snippet/forkoexternalparentprovider/", fileName)
}
