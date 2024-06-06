package com.lemonappdev.konsist.core.declaration.koenumconstant

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoEnumConstantDeclarationForKoTextProviderTest {
    @Test
    fun `enum-const-text`() {
        // given
        val sut =
            getSnippetFile("enum-const-text")
                .classes()
                .first()
                .enumConstants
                .first()

        // then
        assertSoftly(sut) {
            text shouldBeEqualTo "SAMPLE_CONSTANT_1(sampleArgument = 0)"
            hasTextStartingWith("SAMPLE_CONSTANT") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("= 0)") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("CONSTANT_1(sample") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koenumconstant/snippet/forkotextprovider/", fileName)
}
