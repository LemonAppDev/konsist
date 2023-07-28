package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoParentProviderTest {
    @Test
    fun `typealias-without-parent`() {
        // given
        val sut = getSnippetFile("typealias-without-parent")
            .typeAliases
            .first()

        // then
        sut.parent shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/forkoparentprovider/", fileName)
}
