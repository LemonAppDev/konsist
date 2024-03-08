package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoContainingFileProviderTest {
    @Test
    fun `constructor-containing-file`() {
        // given
        val sut =
            getSnippetFile("constructor-containing-file")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koconstructor/snippet/forkocontainingfileprovider/", fileName)
}
