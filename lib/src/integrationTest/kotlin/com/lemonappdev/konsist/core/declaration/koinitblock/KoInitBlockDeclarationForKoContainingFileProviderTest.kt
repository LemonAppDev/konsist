package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoContainingFileProviderTest {
    @Test
    fun `init-block-containing-file`() {
        // given
        val sut = getSnippetFile("init-block-containing-file")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut
            ?.containingFile
            ?.nameWithExtension
            ?.endsWith("file.kt")
            .shouldBeEqualTo(true)
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblock/snippet/forkocontainingfileprovider/", fileName)
}
