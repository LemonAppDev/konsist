package com.lemonappdev.konsist.core.declaration.kosetter

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoSetterDeclarationForKoTextProviderTest {
    @Test
    fun `setter-text`() {
        // given
        val sut =
            getSnippetFile("setter-text")
                .properties()
                .first()
                .setter

        // then
        sut
            ?.text
            .shouldBeEqualTo("set(value) = println(value)")
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/kosetter/snippet/forkotextprovider/", fileName)
}
