package com.lemonappdev.konsist.core.declaration.koinitblock

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.initBlocks
import org.amshove.kluent.assertSoftly
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
        assertSoftly(sut) {
            text.shouldBeEqualTo(
                """
                init {
                        val sampleInitProperty = 6
                    }
                """.trimIndent(),
            )
            hasTextStartingWith("init {") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("}") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("sampleInitProperty =") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koinitblock/snippet/forkotextprovider/", fileName)
}
