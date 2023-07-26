package com.lemonappdev.konsist.core.declaration.kotypealiasdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationTest {
    @Test
    fun `typealias-to-string`() {
        // given
        val sut = getSnippetFile("typealias-to-string")
            .typeAliases
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotypealiasdeclaration/snippet/forgeneral/", fileName)
}
