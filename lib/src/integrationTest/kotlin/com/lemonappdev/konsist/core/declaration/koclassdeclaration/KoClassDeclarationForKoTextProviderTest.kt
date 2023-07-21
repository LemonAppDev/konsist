package com.lemonappdev.konsist.core.declaration.koclassdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.assertSoftly
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoClassDeclarationForKoTextProviderTest {
    @Test
    fun `class-text`() {
        // given
        val sut = getSnippetFile("class-text")
            .classes()
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                    class SampleClass {
                        val sampleProperty = "SampleText"
                    }
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koclassdeclaration/snippet/forkotextprovider/", fileName)
}
