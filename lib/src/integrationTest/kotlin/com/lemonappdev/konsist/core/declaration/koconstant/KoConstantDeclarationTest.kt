package com.lemonappdev.konsist.core.declaration.koconstant

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstantDeclarationTest {
    @Test
    fun `enum-const-to-string`() {
        // given
        val sut = getSnippetFile("enum-const-to-string")
            .classes()
            .first()
            .constants
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koconstant/snippet/forgeneral/", fileName)
}
