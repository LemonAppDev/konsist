package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoSuspendlModifierProviderTest {
    @Test
    fun `function-without-suspend-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-suspend-modifier")
                .functions()
                .first()

        // then
        sut.hasSuspendModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-suspend-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-suspend-modifier")
                .functions()
                .first()

        // then
        sut.hasSuspendModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkosuspendmodifierprovider/", fileName)
}
