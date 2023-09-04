package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationTest {
    @Test
    fun `argument-to-string`() {
        // given
        val sut = getSnippetFile("argument-to-string")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koargument/snippet/forgeneral/", fileName)
}
