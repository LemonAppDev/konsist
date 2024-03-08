package com.lemonappdev.konsist.core.declaration.koclass

import com.lemonappdev.konsist.TestSnippetProvider
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationTest {
    @Test
    fun `class-to-string`() {
        // given
        val sut =
            getSnippetFile("class-to-string")
                .classes()
                .first()

        // then
        sut.toString() shouldBeEqualTo "SampleClass"
    }

    private fun getSnippetFile(fileName: String) =
        TestSnippetProvider.getSnippetKoScope("core/declaration/koclass/snippet/forgeneral/", fileName)
}
