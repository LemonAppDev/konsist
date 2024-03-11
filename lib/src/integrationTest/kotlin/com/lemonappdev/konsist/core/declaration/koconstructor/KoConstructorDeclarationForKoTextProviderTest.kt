package com.lemonappdev.konsist.core.declaration.koconstructor

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoConstructorDeclarationForKoTextProviderTest {
    @Test
    fun `constructor-text`() {
        // given
        val sut =
            getSnippetFile("constructor-text")
                .classes()
                .first()
                .constructors
                .first()

        // then
        sut
            .text
            .shouldBeEqualTo("(val sampleParameter: Int)")
    }

    private fun getSnippetFile(fileName: String) = getSnippetKoScope("core/declaration/koconstructor/snippet/forkotextprovider/", fileName)
}
