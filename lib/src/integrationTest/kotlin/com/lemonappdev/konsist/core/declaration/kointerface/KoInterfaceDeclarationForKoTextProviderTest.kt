package com.lemonappdev.konsist.core.declaration.kointerface

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoInterfaceDeclarationForKoTextProviderTest {
    @Test
    fun `interface-text`() {
        // given
        val sut =
            getSnippetFile("interface-text")
                .interfaces()
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo(
                """
                interface SampleInterface {
                    val sampleProperty: Int
                }
                """.trimIndent(),
            )
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kointerface/snippet/forkotextprovider/", fileName)
}
