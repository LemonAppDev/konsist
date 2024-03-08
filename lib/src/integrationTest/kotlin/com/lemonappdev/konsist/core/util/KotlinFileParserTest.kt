package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.TestSnippetProvider
import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.jupiter.api.Test

class KotlinFileParserTest {
    @Test
    fun `assert-suppress-with-few-parameters-on-declarations-which-items-have-null-parent`() {
        // given
        val sut =
            getSnippetFile("file-with-clrf-line-ending")
                .classes()

        // then
        sut.assertTrue { it.resideInPackage("..mapper") }
    }

    private fun getSnippetFile(fileName: String) = TestSnippetProvider.getSnippetKoScope("core/util/snippet/", fileName)
}
