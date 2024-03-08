package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import com.lemonappdev.konsist.api.ext.list.annotations
import com.lemonappdev.konsist.api.ext.list.arguments
import com.lemonappdev.konsist.api.ext.list.enumConstants
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoNameProviderTest {
    @Test
    fun `argument-in-enum-const-without-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-without-name")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.name shouldBeEqualTo ""
    }

    @Test
    fun `argument-in-enum-const-with-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-enum-const-with-name")
                .classes()
                .enumConstants
                .arguments
                .first()

        // then
        sut.name shouldBeEqualTo "sampleArgument"
    }

    @Test
    fun `argument-in-annotation-without-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-without-name")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.name shouldBeEqualTo ""
    }

    @Test
    fun `argument-in-annotation-with-name`() {
        // given
        val sut =
            getSnippetFile("argument-in-annotation-with-name")
                .functions()
                .annotations
                .arguments
                .first()

        // then
        sut.name shouldBeEqualTo "sampleParameter"
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koargument/snippet/forkonameprovider/", fileName)
}
