package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoTopLevelProviderTest {
    @Test
    fun `typealias-is-top-level`() {
        // given
        val sut = getSnippetFile("typealias-is-top-level")
            .typeAliases
            .first()

        // then
        sut.isTopLevel shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/forkotoplevelprovider/", fileName)
}
