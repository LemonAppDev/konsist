package com.lemonappdev.konsist.core.declaration.koobjectdeclaration

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoObjectDeclarationForKoTextProviderTest {
    @Test
    fun `object-text`() {
        // given
        val sut = getSnippetFile("object-text")
            .objects()
            .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                    object SampleObject {
                        val sampleProperty = 6
                    }
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/koobjectdeclaration/snippet/forkotextprovider/", fileName)
}
