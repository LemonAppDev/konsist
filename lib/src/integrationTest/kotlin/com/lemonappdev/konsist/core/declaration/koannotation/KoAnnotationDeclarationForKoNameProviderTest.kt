package com.lemonappdev.konsist.core.declaration.koannotation

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoAnnotationDeclarationForKoNameProviderTest {
    @Test
    fun `annotation-name`() {
        // given
        val sut =
            getSnippetFile("annotation-name")
                .functions()
                .first()
                .annotations
                .first()

        // then
        assertSoftly(sut) {
            name shouldBeEqualTo "SampleAnnotation"
            hasNameStartingWith("Sample") shouldBeEqualTo true
            hasNameStartingWith("Other") shouldBeEqualTo false
            hasNameEndingWith("tion") shouldBeEqualTo true
            hasNameEndingWith("other") shouldBeEqualTo false
            hasNameContaining("Anno") shouldBeEqualTo true
            hasNameContaining("anno") shouldBeEqualTo false
            hasNameMatching(Regex("[a-zA-Z]+")) shouldBeEqualTo true
            hasNameMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koannotation/snippet/forkonameprovider/", fileName)
}
