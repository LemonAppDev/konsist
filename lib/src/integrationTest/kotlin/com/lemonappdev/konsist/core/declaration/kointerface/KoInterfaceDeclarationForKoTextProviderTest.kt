package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoTextProviderTest {
    @Test
    fun `interface-text`() {
        // given
        val sut =
            getSnippetFile("interface-text")
                .interfaces()
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                interface SampleInterface {
                    val sampleProperty: Int
                }
                """.trimIndent(),
            )

        assertSoftly(sut) {
            text.shouldBeEqualTo(
                """
                interface SampleInterface {
                    val sampleProperty: Int
                }
                """.trimIndent(),
            )
            hasTextStartingWith("interface Sample") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("\n}") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("sampleProperty: Int") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkotextprovider/", fileName)
}
