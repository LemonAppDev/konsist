package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoTextProviderTest {
    @Test
    fun `function-text`() {
        // given
        val sut =
            getSnippetFile("function-text")
                .functions()
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                fun sampleFunction() {
                    val sampleProperty = "SampleText"
                }
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkotextprovider/", fileName)
}
