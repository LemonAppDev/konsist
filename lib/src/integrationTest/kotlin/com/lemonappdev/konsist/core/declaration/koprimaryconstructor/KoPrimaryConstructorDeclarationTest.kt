package com.lemonappdev.konsist.core.declaration.koprimaryconstructor

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPrimaryConstructorDeclarationTest {
    @Test
    fun `primary-constructor-to-string`() {
        // given
        val sut = getSnippetFile("primary-constructor-to-string")
            .classes()
            .first()
            .primaryConstructor

        // then
        sut.toString() shouldBeEqualTo sut?.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koprimaryconstructordeclaration/snippet/forgeneral/", fileName)
}
