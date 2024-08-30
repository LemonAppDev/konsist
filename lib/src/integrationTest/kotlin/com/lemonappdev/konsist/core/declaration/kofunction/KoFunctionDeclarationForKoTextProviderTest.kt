package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
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
        assertSoftly(sut) {
            text.shouldBeEqualTo(
                """
                fun sampleFunction() {
                    val sampleProperty = "SampleText"
                }
                """.trimIndent(),
            )
            hasTextStartingWith("fun sample") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("\n}") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("sampleProperty = ") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^\\d]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kofunction/snippet/forkotextprovider/", fileName)
}
