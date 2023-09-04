package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationTest {
    @Test
    fun `argument-in-enum-const-to-string`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-to-string")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    @Test
    fun `argument-in-annotation-to-string`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-to-string")
            .functions()
            .first()
            .annotations
            .first()
            .arguments
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koargument/snippet/forgeneral/", fileName)
}
