package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoExternalParentProviderTest {
    @Test
    fun `interface-has-no-external-parent`() {
        // given
        val sut = getSnippetFile("interface-has-no-external-parent")
            .interfaces()
            .first()

        // then
        sut.externalParents shouldBeEqualTo emptyList()
    }

    @Test
    fun `interface-has-only-external-parents`() {
        // given
        val sut = getSnippetFile("interface-has-only-external-parents")
            .interfaces()
            .first()

        // then
        sut.externalParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    @Test
    fun `interface-has-internal-and-external-parents`() {
        // given
        val sut = getSnippetFile("interface-has-internal-and-external-parents")
            .interfaces()
            .first()

        // then
        sut.externalParents.map { it.name } shouldBeEqualTo listOf("UnknownParent1", "UnknownParent2")
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kointerface/snippet/forkoexternalparentprovider/", fileName)
}
