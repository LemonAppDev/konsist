package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoExternalParentProviderTest {
    @Test
    fun `class-has-no-external-parent`() {
        // given
        val sut = getSnippetFile("class-has-no-external-parent")
            .classes()
            .first()

        // then
        sut.externalParents shouldBeEqualTo emptyList()
    }

    @Test
    fun `class-has-only-external-parents`() {
        // given
        val sut = getSnippetFile("class-has-only-external-parents")
            .classes()
            .first()

        // then
        sut.externalParents.map { it.name } shouldBeEqualTo listOf("ExternalParent1", "ExternalParent2")
    }

    @Test
    fun `class-has-internal-and-external-parents`() {
        // given
        val sut = getSnippetFile("class-has-internal-and-external-parents")
            .classes()
            .first()

        // then
        sut.externalParents.map { it.name } shouldBeEqualTo listOf("ExternalParent1", "ExternalParent2")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclass/snippet/forkoexternalparentprovider/", fileName)
}
