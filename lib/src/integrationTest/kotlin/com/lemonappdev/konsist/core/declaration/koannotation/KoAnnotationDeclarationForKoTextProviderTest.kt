package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoTextProviderTest {
    @Test
    fun `annotation-text`() {
        // given
        val sut =
            getSnippetFile("annotation-text")
                .functions()
                .first()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            text.shouldBeEqualTo(
                """
                @SampleAnnotationWithParameter(sampleParameter = "text")
                """.trimIndent(),
            )
            hasTextStartingWith("@Sample") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("eter = \"text\")") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("Parameter(sample") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^\\d]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/forkotextprovider/", fileName)
}
