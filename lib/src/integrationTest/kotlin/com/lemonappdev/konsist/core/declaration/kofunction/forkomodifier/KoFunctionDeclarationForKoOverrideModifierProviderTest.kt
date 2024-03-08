package com.lemonappdev.konsist.core.declaration.kofunction.forkomodifier

import com.lemonappdev.konsist.TestSnippetProvider.getSnippetKoScope
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class KoFunctionDeclarationForKoOverrideModifierProviderTest {
    @Test
    fun `function-without-override-modifier`() {
        // given
        val sut =
            getSnippetFile("function-without-override-modifier")
                .functions()
                .first()

        // then
        sut.hasOverrideModifier shouldBeEqualTo false
    }

    @Test
    fun `function-with-override-modifier`() {
        // given
        val sut =
            getSnippetFile("function-with-override-modifier")
                .functions(includeNested = true)
                .first()

        // then
        sut.hasOverrideModifier shouldBeEqualTo true
    }

    private fun getSnippetFile(fileName: String) =
        getSnippetKoScope("core/declaration/kofunction/forkomodifier/snippet/forkooverridemodifierprovider/", fileName)
}
