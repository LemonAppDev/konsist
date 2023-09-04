package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoContainingFileProviderTest {
    @Test
    fun `argument-containing-file`() {
        // given
        val sut = getSnippetFile("argument-containing-file")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkocontainingfileprovider/", fileName)
}
