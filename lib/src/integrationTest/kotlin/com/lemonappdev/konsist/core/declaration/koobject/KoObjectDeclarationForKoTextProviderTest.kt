package com.lemonappdev.konsist.core.declaration.koobject

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoTextProviderTest {
    @Test
    fun `object-text`() {
        // given
        val sut =
            getSnippetFile("object-text")
                .objects()
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                object SampleObject {
                    val sampleProperty = 6
                }
                """.trimIndent(),
            )

        assertSoftly(sut) {
            text.shouldBeEqualTo(
                """
                object SampleObject {
                    val sampleProperty = 6
                }
                """.trimIndent(),
            )
            hasTextStartingWith("object Sample") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("\n}") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("val sampleProperty = 6") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koobject/snippet/forkotextprovider/", fileName)
}
