package com.lemonappdev.konsist.core.declaration.kotype

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeDeclarationTest {
    @Test
    fun `type-to-string`() {
        // given
        val sut = getSnippetFile("type-to-string")
            .properties()
            .first()
            .type

        // then
        sut.toString() shouldBeEqualTo sut?.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kotype/snippet/forgeneral/", fileName)
}
