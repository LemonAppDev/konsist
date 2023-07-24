package com.lemonappdev.konsist.core.declaration.koinitblockdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInitBlockDeclarationForKoTextProviderTest {
    @Test
    fun `init-block-text`() {
        // given
        val sut = getSnippetFile("init-block-text")
            .classes()
            .first()
            .initBlocks
            ?.first()

        // then
        sut
            ?.text
            ?.shouldBeEqualTo(
                """
                init {
                        val sampleInitProperty = 6
                    }
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koinitblockdeclaration/snippet/forkotextprovider/", fileName)
}
