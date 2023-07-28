package com.lemonappdev.konsist.core.declaration.kosecondaryconstructordeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSecondaryConstructorDeclarationForKoContainingFileProviderTest {
    @Test
    fun `secondary-constructor-containing-file`() {
        // given
        val sut = getSnippetFile("secondary-constructor-containing-file")
            .classes()
            .first()
            .secondaryConstructors
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosecondaryconstructordeclaration/snippet/forkocontainingfileprovider/", fileName)
}
