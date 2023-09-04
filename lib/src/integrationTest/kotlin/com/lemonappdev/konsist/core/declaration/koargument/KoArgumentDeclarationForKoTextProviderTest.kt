package com.lemonappdev.konsist.core.declaration.koargument

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoArgumentDeclarationForKoTextProviderTest {
    @Test
    fun `argument-text`() {
        // given
        val sut = getSnippetFile("argument-text")
            .classes()
            .first()
            .enumConstants
            .first()
            .arguments
            .first()

        // then
        sut.text shouldBeEqualTo "sampleArgument = 0"
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koargument/snippet/forkotextprovider/", fileName)
}
