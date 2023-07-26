package com.lemonappdev.konsist.core.declaration.koimportdeclaration

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoImportDeclarationTest {
    @Test
    fun `import-to-string`() {
        // given
        val sut = getSnippetFile("import-to-string")
            .imports
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koimportdeclaration/snippet/forgeneral/", fileName)
}
