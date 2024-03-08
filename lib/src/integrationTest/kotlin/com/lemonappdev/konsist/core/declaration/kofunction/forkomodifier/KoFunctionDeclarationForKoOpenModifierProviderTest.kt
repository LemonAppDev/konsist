package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoOpenModifierProviderTest {
    @Test
    fun `function-without-open-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-open-modifier")
                .functions()
                .first()

        // then
        sut.hasOpenModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-open-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-open-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasOpenModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkoopenmodifierprovider/", fileName)
}
