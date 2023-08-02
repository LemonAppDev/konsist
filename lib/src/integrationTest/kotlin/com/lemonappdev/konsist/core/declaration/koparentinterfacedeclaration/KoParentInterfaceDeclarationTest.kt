package com.lemonappdev.konsist.core.declaration.koparentinterfacedeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoParentInterfaceDeclarationTest {
    @Test
    fun `parent-interface-to-string`() {
        // given
        val sut = getSnippetFile("parent-interface-to-string")
            .classes()
            .first()
            .parentInterfaces
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koparentinterfacedeclaration/snippet/forgeneral/", fileName)
}
