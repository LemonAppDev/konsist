package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoReturnStatementsProviderTest {
    @Test
    fun `property-has-no-return-statements`() {
        // given
        val sut = getSnippetFile("property-has-no-return-statements")
            .properties()
            .first()

        // then
        sut.numReturnStatements shouldBeEqualTo 0
    }

    @Test
    fun `property-has-one-return-statements`() {
        // given
        val sut = getSnippetFile("property-has-one-return-statements")
            .properties()
            .first()

        // then
        sut.numReturnStatements shouldBeEqualTo 1
    }

    @Test
    fun `property-has-two-return-statements`() {
        // given
        val sut = getSnippetFile("property-has-two-return-statements")
            .properties()
            .first()

        // then
        sut.numReturnStatements shouldBeEqualTo 2
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope(
            "core/declaration/koproperty/snippet/forkoreturnstatementsprovider/",
            fileName,
        )
}
