package com.lemonappdev.konsist.core.declaration.koproperty

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoPropertyDeclarationForKoTextProviderTest {
    @Test
    fun `property-text`() {
        // given
        val sut =
            getSnippetFile("property-text")
                .properties()
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo("val sampleProperty = \"\"")
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koproperty/snippet/forkotextprovider/", fileName)
}
