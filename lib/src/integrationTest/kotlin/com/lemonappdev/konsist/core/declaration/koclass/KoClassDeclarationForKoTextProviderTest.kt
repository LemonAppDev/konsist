package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoTextProviderTest {
    @Test
    fun `class-text`() {
        // given
        val sut =
            getSnippetFile("class-text")
                .classes()
                .first()

        // then
        assertSoftly(sut) {
            text.shouldBeEqualTo(
                """
                class SampleClass {
                    val sampleProperty = "SampleText"
                }
                """.trimIndent(),
            )
            hasTextStartingWith("class Sample") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("\n}") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("sampleProperty = ") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^\\d]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koclass/snippet/forkotextprovider/", fileName)
}
