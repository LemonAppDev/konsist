package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationTest {
    @Test
    fun `init-block-to-string`() {
        // given
        val sut = getSnippetFile("init-block-to-string")
            .classes()
            .initBlocks
            .first()

        // then
        sut.toString() shouldBeEqualTo sut.locationWithText
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koinitblock/snippet/forgeneral/", fileName)
}
