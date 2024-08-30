package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoTextProviderTest {
    @Test
    fun `init-block-text`() {
        // given
        val sut =
            getSnippetFile("init-block-text")
                .classes()
                .initBlocks
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                init {
                        val sampleInitProperty = 6
                    }
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koinitblock/snippet/forkotextprovider/", fileName)
}
