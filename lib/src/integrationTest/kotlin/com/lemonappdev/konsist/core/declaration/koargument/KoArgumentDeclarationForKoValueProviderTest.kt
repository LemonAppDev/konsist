package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoValueProviderTest {
    @Test
    fun `argument-in-enum-const-with-value-and-without-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-with-value-and-without-argument-name")
            .classes()
            .enumConstants
            .arguments
            .first()

        // then
        sut.value shouldBeEqualTo "0"
    }

    @Test
    fun `argument-in-enum-const-with-value-and-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-with-value-and-argument-name")
            .classes()
            .enumConstants
            .arguments
            .first()

        // then
        sut.value shouldBeEqualTo "0"
    }

    @Test
    fun `argument-in-annotation-with-value-and-without-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-with-value-and-without-argument-name")
            .functions()
            .annotations
            .arguments
            .first()

        // then
        sut.value shouldBeEqualTo "\"text\""
    }

    @Test
    fun `argument-in-annotation-with-value-and-argument-name`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-with-value-and-argument-name")
            .functions()
            .annotations
            .arguments
            .first()

        // then
        sut.value shouldBeEqualTo "\"text\""
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkovalueprovider/", fileName)
}
