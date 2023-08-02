package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoContainingDeclarationProviderTest {
    @Test
    fun `typealias-without-parent`() {
        // given
        val sut = getSnippetFile("typealias-without-parent")
            .typeAliases
            .first()

        // then
        sut.containingDeclaration shouldBeEqualTo null
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/forkocontainingdeclarationprovider/", fileName)
}
