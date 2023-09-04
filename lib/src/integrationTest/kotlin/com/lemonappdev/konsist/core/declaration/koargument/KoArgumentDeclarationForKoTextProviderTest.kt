package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoTextProviderTest {
    @Test
    fun `argument-in-enum-const-text`() {
        // given
        val sut = getSnippetFile("argument-in-enum-const-text")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.text shouldBeEqualTo "sampleArgument = 0"
    }

    @Test
    fun `argument-in-annotation-text`() {
        // given
        val sut = getSnippetFile("argument-in-annotation-text")
            .functions()
            .first()
            .annotations
            .first()
            .arguments
            .first()

        // then
        sut.text shouldBeEqualTo "sampleParameter = \"text\""
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkotextprovider/", fileName)
}
