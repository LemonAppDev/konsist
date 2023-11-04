package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoContainingFileProviderTest {
    @Test
    fun `setter-containing-file`() {
        // given
        val sut =
            getSnippetFile("setter-containing-file")
                .properties()
                .first()
                .setter

        // then
        sut
            ?.containingFile
            ?.nameWithExtension
            ?.endsWith("file.kt")
            ?.shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kosetter/snippet/forkocontainingfileprovider/", fileName)
}
