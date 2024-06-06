package com.lemonappdev.konsist.core.declaration.kofile

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
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
        assertSoftly(sut) {
            text.shouldBeEqualTo(
                """
                fun sampleFunction() {
                    "SampleText"
                }
                
                """.trimIndent(),
            )
            hasTextStartingWith("fun sampleF") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("}\n") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("Function() {") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope(
            "core/declaration/kofile/snippet/forkotextprovider/",
            fileName,
        )
}
