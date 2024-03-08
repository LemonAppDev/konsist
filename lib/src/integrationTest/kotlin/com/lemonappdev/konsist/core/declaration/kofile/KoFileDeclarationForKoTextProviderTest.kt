package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFileDeclarationForKoTextProviderTest {
    @Test
    fun `file-text`() {
        // given
        val sut =
            getSnippetFile("file-text")
                .files
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

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkotextprovider/",
            fileName,
        )
}
