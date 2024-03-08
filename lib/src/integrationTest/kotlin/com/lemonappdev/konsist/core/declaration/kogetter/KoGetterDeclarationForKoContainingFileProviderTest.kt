package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoContainingFileProviderTest {
    @Test
    fun `getter-containing-file`() {
        // given
        val sut =
            getSnippetFile("getter-containing-file")
                .properties()
                .first()
                .getter

        // then
        sut
            ?.containingFile
            ?.nameWithExtension
            ?.endsWith("file.kt")
            ?.shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kogetter/snippet/forkocontainingfileprovider/", fileName)
}
