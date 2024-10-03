package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.provider.annotations
import com.lemonappdev.konsist.api.ext.list.provider.arguments
import com.lemonappdev.konsist.api.ext.list.provider.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoValueProviderTest {
    @Test
    fun `argument-in-enum-const-with-value-and-without-argument-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-with-value-and-without-argument-name")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "0"
            hasValue() shouldBeEqualTo true
            hasValue("0") shouldBeEqualTo true
            hasValue("10") shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-enum-const-with-value-and-argument-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-with-value-and-argument-name")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "0"
            hasValue() shouldBeEqualTo true
            hasValue("0") shouldBeEqualTo true
            hasValue("10") shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-enum-const-with-multiline-string-argument`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-with-multiline-string-argument")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "first line\n    second line"
            hasValue() shouldBeEqualTo true
            hasValue("first line\n    second line") shouldBeEqualTo true
            hasValue("other text") shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-annotation-with-value-and-without-argument-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-with-value-and-without-argument-name")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "text"
            hasValue() shouldBeEqualTo true
            hasValue("text") shouldBeEqualTo true
            hasValue("other text") shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-annotation-with-value-and-argument-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-with-value-and-argument-name")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "text"
            hasValue() shouldBeEqualTo true
            hasValue("text") shouldBeEqualTo true
            hasValue("other text") shouldBeEqualTo false
        }
    }

    @Test
    fun `argument-in-annotation-with-multiline-string-argument`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-with-multiline-string-argument")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        assertSoftly(sut) {
            value shouldBeEqualTo "first line\n    second line"
            hasValue() shouldBeEqualTo true
            hasValue("first line\n    second line") shouldBeEqualTo true
            hasValue("other text") shouldBeEqualTo false
        }
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koargument/snippet/forkovalueprovider/", fileName)
}
