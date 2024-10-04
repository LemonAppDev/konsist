package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoTextProviderTest {
    @Test
    fun `argument-in-enum-const-text`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-text")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            text shouldBeEqualTo "sampleArgument = 0"
            hasTextStartingWith("sample") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("= 0") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("Argument =") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^@]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-annotation-text`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-text")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            text shouldBeEqualTo "sampleParameter = \"text\""
            hasTextStartingWith("sample") shouldBeEqualTo true
            hasTextStartingWith("Other") shouldBeEqualTo false
            hasTextEndingWith("= \"text\"") shouldBeEqualTo true
            hasTextEndingWith("other") shouldBeEqualTo false
            hasTextContaining("Parameter =") shouldBeEqualTo true
            hasTextContaining("anno") shouldBeEqualTo false
            hasTextMatching(Regex("^[^\\d]*\$")) shouldBeEqualTo true
            hasTextMatching(Regex("[0-9]+")) shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koargument/snippet/forkotextprovider/", fileName)
}
