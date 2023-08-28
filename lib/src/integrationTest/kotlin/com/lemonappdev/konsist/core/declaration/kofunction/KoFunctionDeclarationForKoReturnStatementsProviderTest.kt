package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoReturnStatementsProviderTest {
    @Test
    fun `function-has-no-return-statements`() {
        // given
        val sut = getSnippetFile("function-has-no-return-statements")
            .functions()
            .first()

        // then
        sut.numReturnStatements shouldBeEqualTo 0
    }

    @Test
    fun `function-has-one-return-statements`() {
        // given
        val sut = getSnippetFile("function-has-one-return-statements")
            .functions()
            .first()

        // then
        sut.numReturnStatements shouldBeEqualTo 1
    }

    @Test
    fun `function-has-two-return-statements`() {
        // given
        val sut = getSnippetFile("function-has-two-return-statements")
            .functions()
            .first()

        // then
        sut.numReturnStatements shouldBeEqualTo 2
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/kofunction/snippet/forkoreturnstatementsprovider/",
            fileName,
        )
}
