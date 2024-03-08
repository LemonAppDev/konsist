package com.lemonappdev.konsist.core.declaration.kofunction

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationTest {
    @Test
    fun `function-to-string`() {
        // given
        val sut =
            getSnippetFile("function-to-string")
                .functions()
                .first()

        // then
        sut.toString() shouldBeEqualTo "sampleFunction"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/kofunction/snippet/forgeneral/", fileName)
}
