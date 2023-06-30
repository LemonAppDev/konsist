package com.lemonappdev.konsist.core.container.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileForTextTest {
    @Test
    fun `file-text`() {
        // given
        val sut = getSnippetFile("file-text")
            .files()
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
            fun sampleFunction() {
                "SampleText"
            }
            
            """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/container/kofile/snippet/fortext/", fileName)
}
