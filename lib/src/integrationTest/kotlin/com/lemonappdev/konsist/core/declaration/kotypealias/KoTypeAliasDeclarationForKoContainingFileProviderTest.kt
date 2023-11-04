package com.lemonappdev.konsist.core.declaration.kotypealias

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoTypeAliasDeclarationForKoContainingFileProviderTest {
    @Test
    fun `typealias-containing-file`() {
        // given
        val sut =
            getSnippetFile("typealias-containing-file")
                .typeAliases
                .first()

        // then
        sut
            .containingFile
            .nameWithExtension
            .endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kotypealias/snippet/forkocontainingfileprovider/", fileName)
}
