package com.lemonappdev.konsist.core.declaration.kogetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoGetterDeclarationForKoTextProviderTest {
    @Test
    fun `getter-text`() {
        // given
        val sut =
            getSnippetFile("getter-text")
                .properties()
                .first()
                .getter

        // then
        sut
            ?.text
            .shouldBeEqualTo("get() = \"some text\"")
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kogetter/snippet/forkotextprovider/", fileName)
}
