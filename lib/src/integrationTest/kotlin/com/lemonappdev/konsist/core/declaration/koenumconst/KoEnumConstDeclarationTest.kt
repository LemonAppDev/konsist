package com.lemonappdev.konsist.core.declaration.koenumconst

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstDeclarationTest {
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
        TestSnippetProvider.getSnippetKoScope("core/declaration/koenumconst/snippet/forgeneral/", fileName)
}
