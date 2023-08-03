package com.lemonappdev.konsist.core.declaration.koparentclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentClassDeclarationTest {
    @Test
    fun `parent-class-to-string`() {
        // given
        val sut = getSnippetFile("parent-class-to-string")
            .classes()
            .first()
            .parentClass

        // then
        sut.toString() shouldBeEqualTo sut?.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentclassdeclaration/snippet/forgeneral/", fileName)
}
